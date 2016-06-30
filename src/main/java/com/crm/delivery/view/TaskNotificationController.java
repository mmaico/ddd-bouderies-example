package com.crm.delivery.view;

import com.crm.delivery.application.triggernotification.TaskNotificationApplication;
import com.crm.delivery.view.dtos.TriggerNotificationDTO;
import com.crm.infrastructure.entity.builders.TaskBuilder;
import com.crm.infrastructure.entity.builders.TaskNotificationBuilder;
import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskNotificationController {

    @Autowired
    private TaskNotificationApplication application;


    @RequestMapping(value = "/tasks/{taskId}/trigger-notifications", method = RequestMethod.PUT)
    public  ModelAndView addTriggerNotification(@PathVariable Long taskId, @ModelAttribute TriggerNotificationDTO dto, Model model) {

        ScheduleTriggerNotification triggerNotification = TaskNotificationBuilder.createNotification().withDate(dto.getStart())
                .withTask(TaskBuilder.createTaskBuilder(taskId).build()).build();

        ScheduleTriggerNotification notification = application.register(triggerNotification);

        model.addAttribute("notification", notification);
        return new ModelAndView("/delivery/tasks/includes/task-notification");
    }


}
