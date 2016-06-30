package com.crm.auditing.application;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.auditing.TaskAudinting;
import com.crm.infrastructure.entity.builders.TaskAuditingBuilder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.Pager;
import com.crm.infrastructure.repository.TaskAuditingRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskAuditingService extends BaseModelServiceImpl<TaskAudinting> implements TaskAuditingFacade {


    @Autowired
    private TaskAuditingRepository repository;

    @Autowired
    private Gson gson;


    @Override
    public Optional<TaskAudinting> registerAuditing(Task task, User userThatChanged) {
        Page<TaskAudinting> lasModitication = repository.findLasVersion(task.getId(), Pager.build().withPageSize(1));

        TaskAudinting newEntryAuditable = TaskAuditingBuilder.createAuditing()
                .withEntityId(task.getId())
                .setCurrentDate()
                .withInfo(gson.toJson(task))
                .withUser(userThatChanged).build();

        if (lasModitication.getContent().size() == 0) {
            return Optional.ofNullable(repository.save(newEntryAuditable));
        } else {
            TaskAudinting before = lasModitication.getContent().get(0);
            if (!before.getInfo().equals(newEntryAuditable.getInfo())) {
                TaskAudinting after = repository.save(newEntryAuditable);

                return Optional.ofNullable(after);
            }
        }

        return Optional.empty();
    }

    @Override
    public Page<TaskAudinting> findLogs(Long taskId, Pager pager) {
        return repository.findAll(taskId, pager);
    }

    public BaseRepository<TaskAudinting, Long> getRepository() {
        return this.repository;
    }
}
