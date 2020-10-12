package com.example.test1;

public class Giay  {
    private int Id;
    private String TenGiay;
    private String Gia;
    private String Soluong;


    public Giay(int id, String tenGiay, String gia, String soluong ) {
        Id = id;
        TenGiay = tenGiay;
        Gia = gia;
        Soluong = soluong;
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

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        Soluong = soluong;
    }




}
