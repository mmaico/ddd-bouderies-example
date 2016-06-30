package com.crm.delivery.application.tasks;

import com.crm.delivery.domain.TaskDomainService;
import com.crm.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import com.crm.delivery.infrastructure.generatebysalesorder.SalesOrderTaskItemProcessor;
import com.crm.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.TaskChangeHistoryBuilder;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.task.TaskChangeHistory;
import com.crm.infrastructure.events.messages.TaskChangeMessage;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.task.TaskRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.infrastructure.helpers.HandlerErrors;
import com.crm.infrastructure.helpers.ModelHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class TaskApplicationImpl extends BaseModelServiceImpl<Task> implements TaskApplication {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskChangeHistoryRepository changeHistoryRepository;

    @Autowired
    private TaskDomainService service;

    @Autowired
    private SalesOrderTaskItemProcessor processor;

    @Autowired
    private EventBus eventBus;



    @Override
    public Task register(Task task) {
        Task taskSaved;

        if (!task.isNew()) {
            taskSaved =  super.save(task, service);
        } else {
            service.checkBusinessRulesFor(task);
            service.prepareToSave(task);
            taskSaved = super.save(task);
        }

        if (task.hasValidParent()) {
            Optional<Task> parentLoaded = repository.getOne(task.getParent().getId());
            if (parentLoaded.isPresent()) {
                parentLoaded.get().addChild(taskSaved);
            }
        }
        eventBus.post(TaskChangeMessage.create(taskSaved));
        return taskSaved;
    }

    @Override
    public Task registerSubtask(Task parent, Task taskChild) {


        Optional<Task> taskParentLoaded = repository.getOne(parent.getId());

        if (!taskParentLoaded.isPresent()) {
            throw new ValidationException(Sets.newHashSet("subtask.with.invalid.parent"));
        }

        taskChild.setParent(taskParentLoaded.get());
        taskChild.setSalesOrder(taskParentLoaded.get().getSalesOrder());
        taskChild.setRegion(taskParentLoaded.get().getRegion());


        return register(taskChild);
    }

    @Override
    public void generateTaskByNewSalesOrder(SalesOrder salesOrder) throws Exception {

        List<Task> result = repository.findBySalesOrder(salesOrder);
        if (!result.isEmpty()) {
            HandlerErrors.hasErrors(Sets.newHashSet("already.generate.task.for.this.sale"))
                    .throwing(ValidationException.class);
        }

        List<Task> tasks = processor.process(salesOrder);

        if (tasks.isEmpty()) return;

        this.repository.save(tasks);
    }

    @Override
    public List<Task> findBySaleOrder(SalesOrder salesOrder) {

        HandlerErrors.hasErrors(ModelHelper.isNull(salesOrder) || salesOrder.isNew() ? newHashSet("invalid.salesorder.list.tasks") : emptySet())
                .throwing(ValidationException.class);

        return repository.findBySalesOrder(salesOrder);

    }

    @Override
    public Boolean isSomeonesSon(Task task) {

        if (task == null || task.isNew()) {
            return Boolean.FALSE;
        }

        return repository.isSomeonesSon(task);
    }

    @Override
    public void changeStatus(Task task, User userChange) {

        Task taskLoaded = repository.findOne(task.getId());

        HandlerErrors.hasErrors(ModelHelper.isNull(taskLoaded) ? newHashSet("task.not.found") : emptySet())
                .throwing(ValidationException.class);

        taskLoaded.setStatus(task.getStatus());

        TaskChangeHistory history = TaskChangeHistoryBuilder.createTaskChangeHistory(taskLoaded, task.getStatus()).build();
        this.changeHistoryRepository.save(history);
    }

    @Override
    public DeliveryResumeExecutionTaskDTO getResume() {

        DeliveryResumeExecutionTaskDTO taskDTO = DeliveryResumeExecutionTaskDTO.create()
                .add(TaskStatus.DONE, repository.countByStatus(TaskStatus.DONE))
                .add(TaskStatus.STATED, repository.countByStatus(TaskStatus.STATED))
                .add(TaskStatus.WAITING, repository.countByStatus(TaskStatus.WAITING))
                .add(TaskStatus.PROBLEM, repository.countByStatus(TaskStatus.PROBLEM));

        return taskDTO;
    }

    @Override
    public DeliveryResumeExecutionTaskDTO getResume(SalesOrder salesOrder) {
        DeliveryResumeExecutionTaskDTO taskDTO = DeliveryResumeExecutionTaskDTO.create()
                .add(TaskStatus.DONE, repository.countByStatus(TaskStatus.DONE, salesOrder))
                .add(TaskStatus.STATED, repository.countByStatus(TaskStatus.STATED, salesOrder))
                .add(TaskStatus.WAITING, repository.countByStatus(TaskStatus.WAITING, salesOrder))
                .add(TaskStatus.PROBLEM, repository.countByStatus(TaskStatus.PROBLEM, salesOrder));

        return taskDTO;
    }

    @Override
    public Long countBySalesOrder(SalesOrder salesOrder) {
        if (salesOrder == null || salesOrder.isNew()) return 0l;
        return this.repository.countBySalesOrder(salesOrder);
    }

    @Override
    public void signedTask(User user, Task task) {

        if (task == null || task.isNew()) {
            HandlerErrors.hasErrors(Sets.newHashSet("task.signed.task.is.invalid")).throwing(ValidationException.class);
        }

        Optional<Task> taskLoaded = this.repository.getOne(task.getId());

        if (taskLoaded.isPresent()) {
            if (!taskLoaded.get().hasSigned(user)) {
                taskLoaded.get().addSignedBy(user);
            }
        }
    }

    @Override
    public void unsignedTask(User user, Task task) {
        if (task == null || task.isNew()) {
            HandlerErrors.hasErrors(Sets.newHashSet("task.signed.task.is.invalid")).throwing(ValidationException.class);
        }

        Optional<Task> taskLoaded = this.repository.getOne(task.getId());

        if (taskLoaded.isPresent()) {
            if (taskLoaded.get().hasSigned(user)) {
                if (!isEmptySafe(taskLoaded.get().getSignedBy())) {
                    taskLoaded.get().getSignedBy().remove(user);
                }
            }
        }
    }

    @Override
    public List<Task> findTaskRootBy(SalesOrder salesOrder) {

        if (salesOrder.isNew()) {
            Lists.newArrayList();
        }

        return this.repository.findTaskRootBy(salesOrder);
    }


    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }


}
