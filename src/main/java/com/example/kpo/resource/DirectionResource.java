package com.example.kpo.resource;

import com.example.kpo.entity.Direction;
import com.fasterxml.jackson.annotation.JsonInclude;

public class DirectionResource extends BaseResource{
    private Integer id;
    private String direction_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource[] activityResource;

    public DirectionResource() {}

    public DirectionResource(Direction direction) {
        this.id = direction.getId();
        this.direction_name = direction.getDirection_name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirection_name() {
        return direction_name;
    }

    public void setDirection_name(String direction_name) {
        this.direction_name = direction_name;
    }

    public ActivityResource[] getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource[] activityResource) {
        this.activityResource = activityResource;
    }

    public Direction toEntity() {
        return new Direction(
                this.id,
                this.direction_name
        );
    }
}