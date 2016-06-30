package com.crm.auditing.application;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.auditing.TaskAudinting;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.Pager;
import com.crm.infrastructure.service.ModelService;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface TaskAuditingFacade extends ModelService<TaskAudinting> {

    Optional<TaskAudinting> registerAuditing(Task task, User userThatChanged);

    Page<TaskAudinting> findLogs(Long taskId, Pager pager);
}
