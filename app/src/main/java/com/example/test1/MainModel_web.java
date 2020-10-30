package com.example.test1;

public class MainModel_web {
    Integer langLogo;
    String langName;
    String langlink;

    public MainModel_web(Integer langLogo, String langName, String langlink) {
        this.langLogo = langLogo;
        this.langName = langName;
        this.langlink = langlink;
    }

    public Integer getLangLogo() {
        return langLogo;
    }

    public void setLangLogo(Integer langLogo) {
        this.langLogo = langLogo;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getLanglink() {
        return langlink;
    }

    public void setLanglink(String langlink) {
        this.langlink = langlink;
    }
}
