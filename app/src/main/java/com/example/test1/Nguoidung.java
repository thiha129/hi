package com.example.test1;

public class Nguoidung {
    private int Id;
    private String Tennguoidung;
    private String hovaTen;
    private String Sodienthoai;
    private String matkhau;
    private String ngaytao;

    public String getHovaTen() {
        return hovaTen;
    }

    public void setHovaTen(String hovaTen) {
        this.hovaTen = hovaTen;
    }

    public Nguoidung(int id, String hovaTen, String tennguoidung, String sodienthoai, String matkhau, String ngaytao) {
        Id = id;
        Tennguoidung = tennguoidung;
        Sodienthoai = sodienthoai;
        this.matkhau = matkhau;
        this.ngaytao = ngaytao;
        this.hovaTen = hovaTen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTennguoidung() {
        return Tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        Tennguoidung = tennguoidung;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }
}
