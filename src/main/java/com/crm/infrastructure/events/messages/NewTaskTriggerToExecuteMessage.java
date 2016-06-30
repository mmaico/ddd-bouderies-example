package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.task.Task;

import java.util.Date;

public class NewTaskTriggerToExecuteMessage {

    private Task task;

    private Date triggerDate;

    private User userNotified;

    public NewTaskTriggerToExecuteMessage(Task task, Date triggerDate, User userNotified) {
        this.task = task;
        this.triggerDate = triggerDate;
        this.userNotified = userNotified;
    }

    public static NewTaskTriggerToExecuteMessage create(Task task, Date triggerDate, User userNotified) {
        return new NewTaskTriggerToExecuteMessage(task, triggerDate, userNotified);
    }

    public Task getTask() {
        return task;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public User getUserNotified() {
        return userNotified;
    }
}
