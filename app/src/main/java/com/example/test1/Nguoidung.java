package com.example.test1;

public class Nguoidung {
    private int Id;
    private String Tennguoidung;
    private String matkhau;
    private String hovaTen;
    private String Sodienthoai;
    private String ngaytao;
    private String diachi;

    public Nguoidung(int id, String tennguoidung, String matkhau, String hovaTen, String sodienthoai, String ngaytao, String diachi) {
        Id = id;
        Tennguoidung = tennguoidung;
        this.matkhau = matkhau;
        this.hovaTen = hovaTen;
        Sodienthoai = sodienthoai;
        this.ngaytao = ngaytao;
        this.diachi = diachi;
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

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHovaTen() {
        return hovaTen;
    }

    public void setHovaTen(String hovaTen) {
        this.hovaTen = hovaTen;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
