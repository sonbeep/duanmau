package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DATABASE.CreateBD;
import com.example.myapplication.DTO.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {

    CreateBD createBD;

    public SachDao(Context context){
        createBD = new CreateBD(context);
    }

    public ArrayList<Sach> getAll(){
        SQLiteDatabase db = createBD.getReadableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        String SELECT = "SELECT * FROM "+Sach.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maSach = cursor.getInt(0);
            int maLoai = cursor.getInt(1);
            String tenSach = cursor.getString(2);
            String giaThue = cursor.getString(3);
            Sach sach = new Sach(maSach, maLoai, tenSach, giaThue);
            list.add(sach);
            cursor.moveToNext();

        }
        cursor.close();
        db.close();
        return list;
    }
    public long insert(Sach sach){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Sach.MASACH, sach.getMaSach());
        contentValues.put(Sach.MALOAISACH, sach.getMaLoaiSach());
        contentValues.put(Sach.TENSACH, sach.getTenSach());
        contentValues.put(Sach.GIATHUE, sach.getGiaThue());
        long a = db.insert(Sach.TB_NAME, null, contentValues);
        return a;
    }
    public long update(Sach sach){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Sach.MASACH, sach.getMaSach());
        contentValues.put(Sach.MALOAISACH, sach.getMaLoaiSach());
        contentValues.put(Sach.TENSACH, sach.getTenSach());
        contentValues.put(Sach.GIATHUE, sach.getGiaThue());
        long a = db.update(Sach.TB_NAME, contentValues, "maSach = ?", new String[]{String.valueOf(sach.getMaSach())});
        return a;
    }
    public long delete(int maSach){
        SQLiteDatabase db = createBD.getWritableDatabase();
        long a = db.delete(Sach.TB_NAME, "maSach= "+maSach, null);
        return a;
    }
    public ArrayList<Sach> getSachTheoDK(String sql, String ... a){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maSach = cursor.getInt(0);
            int maLoai = cursor.getInt(1);
            String tenSach = cursor.getString(2);
            String giaThue = cursor.getString(3);
            Sach sach = new Sach(maSach, maLoai, tenSach, giaThue);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public String getTenSach(int id){
        String sql = "SELECT * FROM sach WHERE maSach=?";
        ArrayList<Sach> list = getSachTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenSach();
    }
    public List<Sach> getData(String sql, String ... a){
        SQLiteDatabase db =createBD.getWritableDatabase();
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        if (cursor.moveToNext()){
            Sach sach = new Sach();
            sach.maSach = cursor.getColumnIndex(String.valueOf(0));
            sach.maLoaiSach = cursor.getColumnIndex(String.valueOf(1));
            sach.tenSach = cursor.getString(2);
            sach.giaThue = cursor.getString(3);
            list.add(sach);
        }
        return list;
    }
    public List<Sach> getSearch(String soLuong){
        String sql = "SELECT * FROM sach WHERE giaThue LIKE'%"+soLuong+"%'";
        return getData(sql);
    }

}
