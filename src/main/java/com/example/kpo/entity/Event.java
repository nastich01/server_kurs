package com.example.kpo.entity;

import java.util.Date;

public class Event extends BaseEntity{
    private String event_name;
    private String place;
    private String date;
    private String time;
    private String link;

    public Event(Integer id, String event_name, String place, String date, String time, String link) {
        super(id);
        this.event_name = event_name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.link = link;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
