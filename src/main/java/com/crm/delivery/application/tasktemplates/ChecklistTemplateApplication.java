package com.crm.delivery.application.tasktemplates;


import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.service.ModelService;

import java.util.List;

public interface ChecklistTemplateApplication extends ModelService<ChecklistTemplate> {

    ChecklistTemplate register(ChecklistTemplate checklistTemplate);

    List<ChecklistTemplate> findCheckListBy(TaskTemplate taskTemplate);

    void delete(ChecklistTemplate checklistTemplate);

}
