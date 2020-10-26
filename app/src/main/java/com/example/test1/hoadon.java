package com.example.test1;

public class hoadon {
    int Id;
    String Ten;
    String SDT;
    String DiaChi;
    int SoLuong;
    String Ngay;
    String Tong;

    public hoadon(int id, String ten, String SDT, String diaChi, int soLuong, String ngay, String tong) {
        Id = id;
        Ten = ten;
        this.SDT = SDT;
        DiaChi = diaChi;
        SoLuong = soLuong;
        Ngay = ngay;
        Tong = tong;
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

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getTong() {
        return Tong;
    }

    public void setTong(String tong) {
        Tong = tong;
    }
}
