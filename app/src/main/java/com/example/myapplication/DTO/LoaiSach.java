package com.example.myapplication.DTO;

public class LoaiSach {
    public int  maLoaiSach;
    public String tenLoaiSach;

    public static final String TB_NAME = "loaisach";
    public static final String MALOAISACH = "maLoai";
    public static final String TENLOAISACH = "tenLoai";

    public LoaiSach() {
    }

    public LoaiSach(int maLoaiSach, String tenLoaiSach) {
        this.maLoaiSach = maLoaiSach;
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(int maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }
}
