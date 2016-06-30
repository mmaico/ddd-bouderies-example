package com.crm.timeline.domain.model.activity;


import com.crm.infrastructure.entity.builders.AbstractBuilder;
import com.crm.timeline.domain.model.user.UserInteracted;
import com.crm.timeline.domain.shared.LogActivityTypeEnum;

public class TimelineActivityBuilder extends AbstractBuilder<TimelineActivity> {

	public TimelineActivityBuilder() {
		this.entity = new TimelineActivity();
	}

	public TimelineActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public TimelineActivityBuilder withType(LogActivityTypeEnum type) {
		this.entity.setType(type);
		return this;
	}

	public TimelineActivityBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	public TimelineActivityBuilder withUser(UserInteracted user) {
		this.entity.setUser(user);
		return this;
	}



	public static TimelineActivityBuilder createLogActivity(Long id) {
		return new TimelineActivityBuilder(id);
	}

	public static TimelineActivityBuilder createLogActivity() {
		return new TimelineActivityBuilder();
	}
	


}
