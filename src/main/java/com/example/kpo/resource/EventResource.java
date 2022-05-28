package com.example.kpo.resource;

import com.example.kpo.entity.Event;
import com.example.kpo.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;

public class EventResource extends BaseResource{
    private Integer id;
    private String event_name;
    private String place;
    private String date;
    private String time;
    private String link;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource[] activityResource;

    public EventResource() {}

    public EventResource(Event item) {
        this.id = item.getId();
        this.event_name = item.getEvent_name();
        this.place = item.getPlace();
        this.date = item.getDate();
        this.time = item.getTime();
        this.link = item.getLink();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ActivityResource[] getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource[] activityResource) {
        this.activityResource = activityResource;
    }

    public Event toEntity() {
        return new Event(
                this.id,
                this.event_name,
                this.place,
                this.date,
                this.time,
                this.link
        );
    }
}
