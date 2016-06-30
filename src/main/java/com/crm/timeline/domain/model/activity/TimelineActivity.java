package com.crm.timeline.domain.model.activity;


import com.crm.infrastructure.entity.Identifiable;
import com.crm.timeline.domain.model.user.UserInteracted;
import com.crm.timeline.domain.shared.LogActivityTypeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;


public class TimelineActivity extends Identifiable {

    private Long id;

    private String description = StringUtils.EMPTY;

    private Date creation = new Date();

    private UserInteracted user;

    private LogActivityTypeEnum type;

    public TimelineActivity(){}

    public TimelineActivity(Long id) {
        this.id = id;
    }

    public LogActivityTypeEnum getType() {
        return type;
    }

    public void setType(LogActivityTypeEnum type) {
        this.type = type;
    }

    @Override public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public UserInteracted getUser() {
        return user;
    }

    public void setUser(UserInteracted user) {
        this.user = user;
    }
}
