package com.crm.delivery.infrastructure.helpers;


import com.crm.delivery.application.tasktemplates.TaskTemplateApplication;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.entity.task.TaskTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskTemplateHelpers {

    @Autowired
    private TaskTemplateApplication application;


    public List<TaskTemplate> findTasks(SaleableUnit saleable) {
        return application.findTaskTemplateOnlyRootBy(saleable);
    }

}
