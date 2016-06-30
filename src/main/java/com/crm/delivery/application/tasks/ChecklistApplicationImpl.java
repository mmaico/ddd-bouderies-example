package com.crm.delivery.application.tasks;

import com.crm.infrastructure.entity.task.Checklist;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.task.ChecklistRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistApplicationImpl extends BaseModelServiceImpl<Checklist> implements ChecklistApplication {

    @Autowired
    private ChecklistRepository repository;


    @Override
    public Checklist register(Checklist checklist) {

        return super.save(checklist);
    }

    @Override
    public List<Checklist> findCheckListBy(Task task) {

        if (task == null || task.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(task);
    }

    @Override
    public void completed(Checklist checklist) {

        if (checklist.isNew()) return;

        Optional<Checklist> result = repository.getOne(checklist.getId());

        if(result.isPresent()) {
            result.get().setIsDone(Boolean.TRUE);
        }

    }

    public BaseRepository<Checklist, Long> getRepository() {
        return repository;
    }



}
