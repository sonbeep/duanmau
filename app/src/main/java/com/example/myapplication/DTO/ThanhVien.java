package com.example.myapplication.DTO;

public class ThanhVien {
    public int maThanhVien;
    public String tenThanhVien;
    public String namSinh;
    public String soTaiKhoan;

    public static final String TB_NAME = "thanhvien";
    public static final String MATHANHVIEN = "maTV";
    public static final String TENTHANHVIEN = "hoTenTV";
    public static final String NAMSINH = "namSinh";
    public static final String SOTAIKHOAN = "soTaiKhoan";

    public ThanhVien() {
    }

    public ThanhVien(int maThanhVien, String tenThanhVien, String namSinh, String soTaiKhoan) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.namSinh = namSinh;
        this.soTaiKhoan = soTaiKhoan;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }
}
