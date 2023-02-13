package com.example.myapplication.DTO;

import java.util.Date;

public class PhieuMuon {
    int maPM;
    int maTV;
    int maSach;
    int maTT;
    Date ngayMuon;
    String tienThue;

    public static final String TB_NAME = "phieumuon";
    public static final String MAPM = "maPM";
    public static final String MATV = "maTV";

    public static final String MASACH = "maSach";
    public static final String MATT = "maTT";
    public static final String NGAYMUON = "ngay";
    public static final String TIENTHUE = "tienThue";

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int maTV, int maSach, int maTT, Date ngayMuon, String tienThue) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maSach = maSach;
        this.maTT = maTT;
        this.ngayMuon = ngayMuon;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getTienThue() {
        return tienThue;
    }

    public void setTienThue(String tienThue) {
        this.tienThue = tienThue;
    }
}
