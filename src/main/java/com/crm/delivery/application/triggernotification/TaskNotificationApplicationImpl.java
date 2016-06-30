package com.crm.delivery.application.triggernotification;

import com.crm.delivery.application.WorkspaceApplication;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.events.messages.NewTaskTriggerToExecuteMessage;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.DateHelper;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.task.ScheduleTriggerNotificationRepository;
import com.crm.infrastructure.repository.task.TaskRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskNotificationApplicationImpl extends BaseModelServiceImpl<ScheduleTriggerNotification> implements TaskNotificationApplication {

    @Autowired
    private ScheduleTriggerNotificationRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private EventBus eventBus;


    public BaseRepository<ScheduleTriggerNotification, Long> getRepository() {
        return repository;
    }


    @Override
    public ScheduleTriggerNotification register(ScheduleTriggerNotification notification) {

        if (!notification.isValidTrigger()) {
            throw new ValidationException(Sets.newHashSet("task.notification.invalid.date"));
        }
        Optional<Task> taskLoaded = taskRepository.getOne(notification.getTask().getId());

        if(!taskLoaded.isPresent()) {
            throw new ValidationException(Sets.newHashSet("task.notification.invalid.task"));
        }

        Task task = taskLoaded.get();
        if (!task.getTriggerNotifications().isEmpty()) {
            task.getTriggerNotifications().forEach(value -> repository.delete(value));
            task.getTriggerNotifications().clear();

        }
        ScheduleTriggerNotification triggerSaved = repository.save(notification);

        task.addTriggerNotification(triggerSaved);

        return triggerSaved;
    }

    /**
       Executa a cada 10 min
     **/
    @Scheduled(fixedDelay= 600000 )
    public void sendEventWhenTriggerAvailable() {

        List<ScheduleTriggerNotification> result = repository.findAllAvailableToday(DateHelper.now());

        for (ScheduleTriggerNotification trigger: result) {
            List<User> users = workspaceApplication.findUsersResponsibles(trigger.getTask().getSalesOrder());
            users.forEach(user ->
                    eventBus.post(NewTaskTriggerToExecuteMessage
                            .create(trigger.getTask(), trigger.getTriggerDate(), user))
            );
            trigger.setExecuted(Boolean.TRUE);
        }
    }
}
