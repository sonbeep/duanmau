package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DATABASE.CreateBD;
import com.example.myapplication.DTO.PhieuMuon;
import com.example.myapplication.DTO.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonDao {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    CreateBD createBD;

    public PhieuMuonDao(Context context){
        createBD = new CreateBD(context);
    }
    public ArrayList<PhieuMuon> getAll()  {
        SQLiteDatabase db = createBD.getReadableDatabase();
        ArrayList<PhieuMuon> list = new ArrayList<>();
        String SELECT = "SELECT * FROM "+PhieuMuon.TB_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int maPM = cursor.getInt(0);
            int maTV = cursor.getInt(1);
            int maSach = cursor.getInt(2);
            int maTT = cursor.getInt(3);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String tien = cursor.getString(5);

            PhieuMuon phieuMuon = new PhieuMuon(maPM, maTV, maSach, maTT, ngay, tien);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public long insert(PhieuMuon phieuMuon){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PhieuMuon.MAPM, phieuMuon.getMaPM());
        contentValues.put(PhieuMuon.MATV, phieuMuon.getMaTV());
        contentValues.put(PhieuMuon.MASACH, phieuMuon.getMaSach());
        contentValues.put(PhieuMuon.MATT, phieuMuon.getMaTT());
        contentValues.put(PhieuMuon.NGAYMUON, format.format(phieuMuon.getNgayMuon()));
        contentValues.put(PhieuMuon.TIENTHUE, phieuMuon.getTienThue());
        long a =db.insert(PhieuMuon.TB_NAME, null, contentValues);
        return a;
    }
    public long update(PhieuMuon phieuMuon){
        SQLiteDatabase db = createBD.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PhieuMuon.MAPM, phieuMuon.getMaPM());
        contentValues.put(PhieuMuon.MATV, phieuMuon.getMaTV());
        contentValues.put(PhieuMuon.MASACH, phieuMuon.getMaSach());
        contentValues.put(PhieuMuon.MATT, phieuMuon.getMaTT());
        contentValues.put(PhieuMuon.NGAYMUON, format.format(phieuMuon.getNgayMuon()));
        contentValues.put(PhieuMuon.TIENTHUE, phieuMuon.getTienThue());
        long a =db.update(PhieuMuon.TB_NAME, contentValues, "maPM = ?", new String[]{String.valueOf(phieuMuon.getMaPM())} );
        return a;
    }
    public long delete(int maPM){
        SQLiteDatabase db = createBD.getWritableDatabase();
        long a = db.delete(PhieuMuon.TB_NAME, "maPM = "+maPM, null);
        return a;
    }
    public List<PhieuMuon> top10(){
        SQLiteDatabase db = createBD.getReadableDatabase();
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT maSach, count(maSach) as soLuong FROM phieumuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        Cursor cursor =db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

        }
        cursor.close();
        db.close();
        return list;
    }
}
