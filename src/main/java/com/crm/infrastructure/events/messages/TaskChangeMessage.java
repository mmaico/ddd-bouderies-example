package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.task.Task;

public class TaskChangeMessage {


    private User user;
    private Task task;

    public TaskChangeMessage(Task task) {
        this.task = task;
    }

    public static TaskChangeMessage create(Task task) {
          return new TaskChangeMessage(task);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
