package com.crm.delivery.domain;


import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.service.DomainBusinessRules;

public interface TaskDomainService extends DomainBusinessRules<Task> {


    void prepareToSave(Task task);
}
