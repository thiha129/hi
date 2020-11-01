package com.example.test1;

public class Giay {
    int Id;
    String TenGiay;
    int Gia;
    String Soluong;
    String LinkAnh;
    String Chitiet;

    public Giay(int id, String tenGiay, int gia, String soluong, String linkAnh, String chitiet) {
        Id = id;
        TenGiay = tenGiay;
        Gia = gia;
        Soluong = soluong;
        LinkAnh = linkAnh;
        Chitiet = chitiet;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenGiay() {
        return TenGiay;
    }

    public void setTenGiay(String tenGiay) {
        TenGiay = tenGiay;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        Soluong = soluong;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getChitiet() {
        return Chitiet;
    }

    public void setChitiet(String chitiet) {
        Chitiet = chitiet;
    }
}
