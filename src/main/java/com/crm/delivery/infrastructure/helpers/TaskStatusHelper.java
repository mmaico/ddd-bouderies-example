package com.crm.delivery.infrastructure.helpers;


import com.crm.infrastructure.entity.enums.TaskStatus;


public class TaskStatusHelper {

    public TaskStatus waiting() {
        return TaskStatus.WAITING;
    }

    public TaskStatus done() {
        return TaskStatus.DONE;
    }

    public TaskStatus problem() {
        return TaskStatus.PROBLEM;
    }

    public TaskStatus started() {
        return TaskStatus.STATED;
    }
}
