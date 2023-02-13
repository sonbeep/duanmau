package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.DATABASE.CreateBD;
import com.example.myapplication.DTO.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDao {
    CreateBD createBD;

    public LoaiSachDao(Context context){
        createBD = new CreateBD(context);
    }

    public ArrayList<LoaiSach> getAll(){
        SQLiteDatabase db = createBD.getReadableDatabase();
        ArrayList<LoaiSach> loaiSachArrayList = new ArrayList<>();
        String SELECT ="SELECT * FROM "+LoaiSach.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maLoaiSach = cursor.getInt(0);
            String tenLoaiSach = cursor.getString(1);
            LoaiSach loaiSach = new LoaiSach(maLoaiSach, tenLoaiSach);
            loaiSachArrayList.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return loaiSachArrayList;
    }
    public long insert(LoaiSach loaiSach){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiSach.MALOAISACH, loaiSach.getMaLoaiSach());
        contentValues.put(LoaiSach.TENLOAISACH, loaiSach.getTenLoaiSach());
        long a = db.insert(LoaiSach.TB_NAME, null,contentValues);
        return a;
    }
    public long update(LoaiSach loaiSach){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiSach.MALOAISACH, loaiSach.getMaLoaiSach());
        contentValues.put(LoaiSach.TENLOAISACH, loaiSach.getTenLoaiSach());
        long a = db.update(LoaiSach.TB_NAME, contentValues, "maLoai =?", new String[]{String.valueOf(loaiSach.getMaLoaiSach())});
        return a;
    }
    public long delete(int maLoai){
        SQLiteDatabase db = createBD.getWritableDatabase();
        long a = db.delete(LoaiSach.TB_NAME, "maLoai = "+maLoai, null);
        return a;
    }

    public ArrayList<LoaiSach>getLoaiSachTheoDK(String sql, String ... a){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ArrayList<LoaiSach> list= new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int ma =cursor.getInt(0);
            String tenSach = cursor.getString(1);
            LoaiSach loaiSach = new LoaiSach(ma, tenSach);
            list.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public String getTenLoaiSach(int id){
        String sql = "SELECT * FROM loaiSach WHERE maLoai=?";
        ArrayList<LoaiSach> list= getLoaiSachTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenLoaiSach();
    }



}
