package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.task.TaskChangeHistory;

import java.util.Date;

public class TaskChangeHistoryBuilder extends AbstractBuilder<TaskChangeHistory>  {

	public TaskChangeHistoryBuilder() {
		this.entity = new TaskChangeHistory();
	}

	public TaskChangeHistoryBuilder(Task task, TaskStatus status) {
		this();
		this.entity.setStatusChanged(status);
        this.entity.setTaskChanged(task);
        this.entity.setDateOfChange(new Date());
	}

	public static TaskChangeHistoryBuilder createTaskChangeHistory(Task task, TaskStatus status) {
		return new TaskChangeHistoryBuilder(task, status);
	}

}
