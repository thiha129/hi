package com.example.test1;

public class gioHang  {
    int Id;
    String Ten;
    int Gia;
    String size;
    String linkImage;

    public gioHang(int id, String ten, int gia, String size, String linkImage) {
        Id = id;
        Ten = ten;
        Gia = gia;
        this.size = size;
        this.linkImage = linkImage;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
