package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.auditing.TaskAudinting;

import java.util.Calendar;

public class TaskAuditingBuilder extends AbstractBuilder<TaskAudinting>  {

	public TaskAuditingBuilder() {
		this.entity = new TaskAudinting();
	}

	public TaskAuditingBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public TaskAuditingBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}
	
	public TaskAuditingBuilder withInfo(String info) {
		this.entity.setInfo(info);
		return this;
	}
	
	public TaskAuditingBuilder setCurrentDate() {
		this.entity.setLastUpdate(Calendar.getInstance().getTime());
		return this;
	}

	public TaskAuditingBuilder withEntityId(Long entityId) {
		this.entity.setEntityId(entityId);
		return this;
	}

	public static TaskAuditingBuilder createAuditing(Long id) {
		return new TaskAuditingBuilder(id);
	}

	public static TaskAuditingBuilder createAuditing() {
		return new TaskAuditingBuilder();
	}


}
