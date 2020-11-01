package com.example.test1;

public class hoadon {
    int Id;
    String Ten;
    String SDT;
    String DiaChi;
    int SoLuong;
    int Tong;
    String Ngay;

    public hoadon(int id, String ten, String SDT, String diaChi, int soLuong, int tong, String ngay) {
        Id = id;
        Ten = ten;
        this.SDT = SDT;
        DiaChi = diaChi;
        SoLuong = soLuong;
        Tong = tong;
        Ngay = ngay;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getTong() {
        return Tong;
    }

    public void setTong(int tong) {
        Tong = tong;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }
}
