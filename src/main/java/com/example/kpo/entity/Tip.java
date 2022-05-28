package com.example.kpo.entity;

public class Tip extends BaseEntity{
    private String tip_name;

    public Tip(Integer id, String tip_name) {
        super(id);
        this.tip_name = tip_name;
    }

    public String getTip_name() {
        return tip_name;
    }

    public void setTip_name(String tip_name) {
        this.tip_name = tip_name;
    }
}
