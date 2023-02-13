package com.example.myapplication.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateBD extends SQLiteOpenHelper {
    public static final String DB_NAME = "duanmau21";
    public static final int DB_VERSION = 1;
    public CreateBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cr_tb_loaisach = "CREATE TABLE loaisach (maLoai INTEGER PRIMARY KEY, tenLoai TEXT NOT NULL)";
        db.execSQL(cr_tb_loaisach);
        String cr_tb_thanhvien = "CREATE TABLE thanhvien (maTV INTEGER PRIMARY KEY, hoTenTV TEXT NOT NULL, namSinh TEXT NOT NULL, soTaiKhoan TEXT NOT NULL)";
        db.execSQL(cr_tb_thanhvien);
        String cr_tb_thuthu = "CREATE TABLE thuthu (maTT INTEGER PRIMARY KEY, hoTenTT TEXT NOT NULL, matKhau TEXT NOT NULL)";
        db.execSQL(cr_tb_thuthu);
        String cr_tb_sach = "CREATE TABLE sach (maSach INTEGER PRIMARY KEY, maLoai INTEGER NOT NULL REFERENCES tb_loaisach(maloai), tenSach TEXT NOT NULL, giaThue TEXT NOT NULL)";
        db.execSQL(cr_tb_sach);
        String cr_tb_phieumuon = "CREATE TABLE phieumuon (maPM INTEGER PRIMARY KEY, maTV INTEGER NOT NULL REFERENCES tb_thanhvien(maTV), maSach INTEGER NOT NULL REFERENCES tb_sach(maSach), maTT INTEGER NOT NULL REFERENCES tb_thuthu(maTT), ngay DATE,  tienThue TEXT NOT NULL)";
        db.execSQL(cr_tb_phieumuon);

        String insert_thanhvien = "INSERT INTO thanhvien VALUES(10, 'Son', '2002', '000012')";
        db.execSQL(insert_thanhvien);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_tb_loaisach = "DROP TABLE IF EXISTS tb_loaisach";
        db.execSQL(drop_tb_loaisach);
        String drop_tb_thanhvien = "DROP TABLE IF EXISTS tb_thanhvien";
        db.execSQL(drop_tb_thanhvien);
        String drop_tb_thuthu = "DROP TABLE IF EXISTS tb_thuthu ";
        db.execSQL(drop_tb_thuthu);
        String drop_tb_sach = "DROP TABLE IF EXISTS tb_sach";
        db.execSQL(drop_tb_sach);
        String drop_tb_phieumuon = "DROP TABLE IF EXISTS tb_phieumuon";
        db.execSQL(drop_tb_phieumuon);
        onCreate(db);

    }
}
