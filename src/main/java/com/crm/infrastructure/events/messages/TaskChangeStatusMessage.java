package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.task.Task;

public class TaskChangeStatusMessage {


    private User user;
    private TaskStatus oldStatus;
    private Task task;

    public TaskChangeStatusMessage(User user, Task task, TaskStatus oldStatus) {
        this.user = user;
        this.task = task;
        this.oldStatus = oldStatus;
    }

    public static TaskChangeStatusMessage create(Task task, User userChange, TaskStatus oldStatus) {
          return new TaskChangeStatusMessage(userChange, task, oldStatus);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(TaskStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
