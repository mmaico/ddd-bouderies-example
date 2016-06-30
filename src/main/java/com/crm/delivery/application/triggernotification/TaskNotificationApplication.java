package com.crm.delivery.application.triggernotification;


import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.service.ModelService;

public interface TaskNotificationApplication extends ModelService<ScheduleTriggerNotification> {


    ScheduleTriggerNotification register(ScheduleTriggerNotification notification);

}
