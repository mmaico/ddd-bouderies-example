package com.crm.delivery.application.tasktemplates;


import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.service.ModelService;

import java.util.List;

public interface TaskTemplateApplication extends ModelService<TaskTemplate> {

    TaskTemplate register(TaskTemplate taskTemplate);

    List<TaskTemplate> findTaskTemplateBy(SaleableUnit saleable);

    List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnit saleable);

    void remove(TaskTemplate taskTemplate);

}
