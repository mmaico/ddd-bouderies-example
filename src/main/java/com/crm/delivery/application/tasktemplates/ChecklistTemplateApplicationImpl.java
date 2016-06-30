package com.crm.delivery.application.tasktemplates;

import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.task.ChecklistTemplateRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistTemplateApplicationImpl extends BaseModelServiceImpl<ChecklistTemplate> implements ChecklistTemplateApplication {

    @Autowired
    private ChecklistTemplateRepository repository;


    @Override
    public ChecklistTemplate register(ChecklistTemplate checklistTemplate) {

        return super.save(checklistTemplate);
    }

    @Override
    public List<ChecklistTemplate> findCheckListBy(TaskTemplate taskTemplate) {

        if (taskTemplate == null || taskTemplate.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(taskTemplate);
    }

    @Override
    public void delete(ChecklistTemplate checklistTemplate) {
        repository.delete(checklistTemplate);
    }

    public BaseRepository<ChecklistTemplate, Long> getRepository() {
        return repository;
    }



}
