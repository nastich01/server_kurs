package com.example.kpo.entity;

public class Direction extends BaseEntity{
    private String direction_name;

    public Direction(Integer id, String direction_name) {
        super(id);
        this.direction_name = direction_name;
    }

    public String getDirection_name() {
        return direction_name;
    }

    public void setDirection_name(String direction_name) {
        this.direction_name = direction_name;
    }
}
