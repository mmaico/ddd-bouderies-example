package com.crm.delivery.application.tasktemplates;

import com.crm.delivery.domain.TaskTemplateDomainService;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.task.TaskTemplateRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class TaskTemplateApplicationImpl extends BaseModelServiceImpl<TaskTemplate> implements TaskTemplateApplication {

    @Autowired
    private TaskTemplateRepository repository;

    @Autowired
    private TaskTemplateDomainService domainService;


    @Override
    public TaskTemplate register(TaskTemplate taskTemplate) {
        TaskTemplate templateSaved = super.save(taskTemplate, domainService);

        if (templateSaved.hasValidParent()) {
            TaskTemplate parent = repository.findOne(templateSaved.getParent().getId());
            parent.addChild(templateSaved);
        }

        return templateSaved;
    }

    @Override
    public List<TaskTemplate> findTaskTemplateBy(SaleableUnit saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateBy(saleable);
    }

    @Override
    public List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnit saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateRootBy(saleable);
    }

    @Override
    public void remove(TaskTemplate taskTemplate) {

        if (taskTemplate == null || taskTemplate.isNew()) {
            hasErrors(Sets.newHashSet("tasktemplate.not.id")).throwing(ValidationException.class);
        }
        Optional<TaskTemplate> result = repository.getOne(taskTemplate.getId());

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        TaskTemplate tasktemplateLoaded = result.get();

        repository.delete(tasktemplateLoaded);
    }

    public Optional<TaskTemplate> getOne(Long id) {

        Optional<TaskTemplate> result = repository.getOne(id);

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        Optional<TaskTemplate> parent = repository.findParent(result.get());

        result.get().setParent(parent.isPresent() ? parent.get() : null);

        return result;
    }

    public BaseRepository<TaskTemplate, Long> getRepository() {
        return repository;
    }


}
