package com.example.myapplication.DTO;

public class Sach {
    public int maSach;
    public int maLoaiSach;
    public String tenSach;
    public String giaThue;

    public static final String TB_NAME = "sach";
    public static final String MASACH = "maSach";
    public static final String MALOAISACH = "maLoai";
    public static final String TENSACH = "tenSach";
    public static final String GIATHUE = "giaThue";

    public Sach() {
    }

    public Sach(int maSach, int maLoaiSach, String tenSach, String giaThue) {
        this.maSach = maSach;
        this.maLoaiSach = maLoaiSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int  getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(int maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(String giaThue) {
        this.giaThue = giaThue;
    }
}
