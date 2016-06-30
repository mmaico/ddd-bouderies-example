package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.entity.task.Task;

import java.util.Date;

public class TaskNotificationBuilder extends AbstractBuilder<ScheduleTriggerNotification>  {

	public TaskNotificationBuilder() {
		this.entity = new ScheduleTriggerNotification();
	}

	public TaskNotificationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public TaskNotificationBuilder withDate(Date date) {
		this.entity.setTriggerDate(date);
		return this;
	}

	public TaskNotificationBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}





	public static TaskNotificationBuilder createNotification(Long id) {
		return new TaskNotificationBuilder(id);
	}

	public static TaskNotificationBuilder createNotification() {
		return new TaskNotificationBuilder();
	}
	


}
