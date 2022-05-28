package com.example.kpo.entity;

public class Dolzh extends BaseEntity{
    private String dolzh_name;

    public Dolzh(Integer id, String dolzh_name) {
        super(id);
        this.dolzh_name = dolzh_name;
    }

    public String getDolzh_name() {
        return dolzh_name;
    }

    public void setDolzh_name(String dolzh_name) {
        this.dolzh_name = dolzh_name;
    }
}
