package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.timeline.items.TaskActivity;

public class TimelineActivityBuilder extends AbstractBuilder<TaskActivity>  {

	public TimelineActivityBuilder() {
		this.entity = new TaskActivity();
	}

	public TimelineActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public TimelineActivityBuilder withDescription(String value) {
        this.entity.setDescription(value);
        return this;
    }

    public TimelineActivityBuilder withUser(Long userId) {
        this.entity.setUser(UserBuilder.createUser(userId).build());
        return this;
    }


	public static TimelineActivityBuilder createActivity() {
		return new TimelineActivityBuilder();
	}


}
