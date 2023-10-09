package com.example.mob2041_hinhnvph42207.model;


public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private int tienThue, TrangThai;
    private String ngayThue;
    private String hoTenTV, tenSach;


    public PhieuMuon() {
    }

    public PhieuMuon(String maTT, int maTV, int maSach, int tienThue, int trangThai, String ngayThue) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        TrangThai = trangThai;
        this.ngayThue = ngayThue;
    }

    public PhieuMuon(int maPM,String hoTenTV, String tenSach, int tienThue, int trangThai, String ngayThue) {
        this.maPM = maPM;
        this.hoTenTV = hoTenTV;
        this.tenSach = tenSach;
        this.tienThue = tienThue;
        TrangThai = trangThai;
        this.ngayThue = ngayThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
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

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getHoTenTV() {
        return hoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        this.hoTenTV = hoTenTV;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
