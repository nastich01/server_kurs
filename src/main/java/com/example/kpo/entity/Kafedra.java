package com.example.kpo.entity;

public class Kafedra extends BaseEntity{
    private String kafedra_name;

    public Kafedra(Integer id, String kafedra_name) {
        super(id);
        this.kafedra_name = kafedra_name;
    }

    public String getKafedra_name() {
        return kafedra_name;
    }

    public void setKafedra_name(String kafedra_name) {
        this.kafedra_name = kafedra_name;
    }
}
