package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DATABASE.CreateBD;
import com.example.myapplication.DTO.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    private Context context;
    CreateBD createBD;

    public ThanhVienDao(Context context){
        createBD = new CreateBD(context);
    }
    public ArrayList<ThanhVien> getAll(){
        SQLiteDatabase db = createBD.getReadableDatabase();
        ArrayList<ThanhVien> thanhVienArrayList = new ArrayList<>();
        String SELECT = "SELECT * FROM "+ ThanhVien.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maTV = cursor.getInt(0);
            String tenTV = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String stk = cursor.getString(3);
            ThanhVien thanhVien = new ThanhVien(maTV, tenTV, namSinh, stk);
            thanhVienArrayList.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return thanhVienArrayList;

    }
    public long insert(ThanhVien thanhVien){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThanhVien.MATHANHVIEN, thanhVien.getMaThanhVien());
        contentValues.put(ThanhVien.TENTHANHVIEN, thanhVien.getTenThanhVien());
        contentValues.put(ThanhVien.NAMSINH, thanhVien.getNamSinh());
        contentValues.put(ThanhVien.SOTAIKHOAN, thanhVien.getSoTaiKhoan());
        long a = db.insert(ThanhVien.TB_NAME, null, contentValues);
        return a;
    }
    public long update(ThanhVien thanhVien){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThanhVien.MATHANHVIEN, thanhVien.getMaThanhVien());
        contentValues.put(ThanhVien.TENTHANHVIEN, thanhVien.getTenThanhVien());
        contentValues.put(ThanhVien.NAMSINH, thanhVien.getNamSinh());
        long a = db.update(ThanhVien.TB_NAME, contentValues, "maTV = ? ", new String[]{String.valueOf(thanhVien.getMaThanhVien())});
        return a;
    }
    public long delete(int maTV){
        SQLiteDatabase db = createBD.getWritableDatabase();
        long a = db.delete(ThanhVien.TB_NAME, "maTV = "+maTV, null);
        return a;

    }
    public ArrayList<ThanhVien> getThanhVienTheoDK(String sql, String ... a){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maTV = cursor.getInt(0);
            String tenTV = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String stk = cursor.getString(3);

            ThanhVien thanhVien = new ThanhVien(maTV, tenTV, namSinh, stk);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public String getTenThanhVien(int id){
        String sql = "SELECT * FROM thanhvien WHERE maTV=?";
        ArrayList<ThanhVien> list = getThanhVienTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenThanhVien();
    }
}
