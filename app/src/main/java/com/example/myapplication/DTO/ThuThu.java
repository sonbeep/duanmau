package com.example.myapplication.DTO;

public class ThuThu {
    public  int maThuThu;
    public String tenThuThu;
    public String matKhau;

    public static final String TB_NAME = "thuthu";
    public static final String MATHUTHU = "maTT";
    public static final String TENTHUTHU = "hoTenTT";
    public static final String MATKHAU = "matKhau";

    public ThuThu() {
    }

    public ThuThu(int maThuThu, String tenThuThu, String matKhau) {
        this.maThuThu = maThuThu;
        this.tenThuThu = tenThuThu;
        this.matKhau = matKhau;
    }

    public int getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(int maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
