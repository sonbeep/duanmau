package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DATABASE.CreateBD;
import com.example.myapplication.DTO.ThuThu;

import java.util.ArrayList;

public class ThuThuDao {
    CreateBD createBD;

    public ThuThuDao(Context context){
        createBD = new CreateBD(context);
    }

    public ArrayList<ThuThu> getAll(){
        SQLiteDatabase db = createBD.getReadableDatabase();
        ArrayList<ThuThu> list = new ArrayList<>();
        String SELECT = "SELECT *FROM "+ThuThu.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String matKhau = cursor.getString(2);

            ThuThu thuThu = new ThuThu(ma, ten, matKhau);
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public long insert(ThuThu thuThu){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuThu.MATHUTHU, thuThu.getMaThuThu());
        contentValues.put(ThuThu.TENTHUTHU, thuThu.getTenThuThu());
        contentValues.put(ThuThu.MATKHAU, thuThu.getMatKhau());
        long a = db.insert(ThuThu.TB_NAME, null, contentValues);
        return a;
    }
    public long update(ThuThu thuThu){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuThu.MATHUTHU, thuThu.getMaThuThu());
        contentValues.put(ThuThu.TENTHUTHU, thuThu.getTenThuThu());
        contentValues.put(ThuThu.MATKHAU, thuThu.getMatKhau());
        long a = db.update(ThuThu.TB_NAME, contentValues, "maTT=?", new String[]{String.valueOf(thuThu.getMaThuThu())});
        return a;
    }
    public long delete(int maThuThu){
        SQLiteDatabase db = createBD.getWritableDatabase();
        long a = db.delete(ThuThu.TB_NAME,  "maTT = "+maThuThu,null);
        return a;
    }
    public ArrayList<ThuThu> getThuThuTheoDK(String sql, String ... a){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ArrayList<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maTT = cursor.getInt(0);
            String tenTT = cursor.getString(1);
            String mk = cursor.getString(2);
            ThuThu thuThu = new ThuThu(maTT, tenTT, mk);
            list.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public String getTenThuThu(int id){
        String sql = "SELECT * FROM thuthu WHERE maTT=?";
        ArrayList<ThuThu> list = getThuThuTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenThuThu();
    }

}
