package com.example.test1;

public class noti {
    String tille;
    String mesage;

    public noti(Object o, String tille, String mesage) {
        this.tille = tille;
        this.mesage = mesage;
    }

    public String getTille() {
        return tille;
    }

    public void setTille(String tille) {
        this.tille = tille;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }
}
