package com.crm.delivery.infrastructure.helpers;


import com.crm.delivery.application.tasks.TaskApplication;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.crm.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Component
public class TaskHelper {

    private static Map<TaskStatus, String> mapStatus = new HashMap<>();

    static {
        mapStatus.put(TaskStatus.WAITING, "icon entypo-right-open-big");
        mapStatus.put(TaskStatus.STATED, "icon entypo-clock");
        mapStatus.put(TaskStatus.DONE, "icon entypo-check");
        mapStatus.put(TaskStatus.PROBLEM, "icon entypo-cancel");
    }

    @Autowired
    private TaskApplication application;




    public List<Task> getTasks(SalesOrder salesOrder) {
        return application.findBySaleOrder(salesOrder);
    }

    public Task load(Task task) {
        Optional<Task> result = application.getOne(task.getId());

        return result.isPresent() ? result.get() : null;
    }

    public String getIconStatus(TaskStatus status) {

        return mapStatus.get(status);
    }

    public Boolean isSomeonesSon(Task task) {
        return application.isSomeonesSon(task);
    }

    public List<Task> findTaskRootBy(SalesOrder salesOrder) {
        return application.findTaskRootBy(salesOrder);
    }


    public ScheduleTriggerNotification getValidTaskNotification(Task task) {

        if (!isEmptySafe(task.getTriggerNotifications())) {
            return task.getTriggerNotifications().get(0);
        }

        return null;
    }
}
