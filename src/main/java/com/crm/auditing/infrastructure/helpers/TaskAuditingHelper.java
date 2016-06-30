package com.crm.auditing.infrastructure.helpers;

import com.crm.infrastructure.entity.auditing.TaskAudinting;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.Pager;
import com.crm.infrastructure.repository.TaskAuditingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskAuditingHelper {

    @Autowired
    private TaskAuditingRepository repository;

    public Iterable<TaskAudinting> findBy(Task task, Pager pager) {

        return repository.findAll(task.getId(), pager);
    }
}
