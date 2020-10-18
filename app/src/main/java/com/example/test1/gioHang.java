package com.example.test1;

public class gioHang  {
    int Id;
    String Ten;
    int Gia;

    public gioHang(int id, String ten, int gia) {
        Id = id;
        Ten = ten;
        Gia = gia;
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
}
