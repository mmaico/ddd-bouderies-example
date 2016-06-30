package com.crm.delivery.application.tasks;


import com.crm.infrastructure.entity.task.Checklist;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.service.ModelService;

import java.util.List;

public interface ChecklistApplication extends ModelService<Checklist> {

    Checklist register(Checklist checklist);

    List<Checklist> findCheckListBy(Task task);

    void completed(Checklist checklist);

}
