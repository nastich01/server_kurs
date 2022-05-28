package com.example.kpo.resource;

import com.example.kpo.entity.Tip;
import com.fasterxml.jackson.annotation.JsonInclude;

public class TipResource extends BaseResource{
    private Integer id;
    private String tip_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource[] activityResource;

    public TipResource() {}

    public TipResource(Tip tip) {
        this.id = tip.getId();
        this.tip_name = tip.getTip_name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTip_name() {
        return tip_name;
    }

    public void setTip_name(String tip_name) {
        this.tip_name = tip_name;
    }

    public ActivityResource[] getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource[] activityResource) {
        this.activityResource = activityResource;
    }

    public Tip toEntity() {
        return new Tip(
                this.id,
                this.tip_name
        );
    }
}
