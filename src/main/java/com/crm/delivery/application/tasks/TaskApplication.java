package com.crm.delivery.application.tasks;


import com.crm.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.service.ModelService;

import java.util.List;

public interface TaskApplication extends ModelService<Task> {

    Task register(Task task);

    Task registerSubtask(Task parent, Task taskChild);

    void generateTaskByNewSalesOrder(SalesOrder salesOrder) throws Exception;

    List<Task> findBySaleOrder(SalesOrder salesOrder);

    Boolean isSomeonesSon(Task task);

    void changeStatus(Task task, User userChange);

    DeliveryResumeExecutionTaskDTO getResume();

    DeliveryResumeExecutionTaskDTO getResume(SalesOrder salesOrder);

    Long countBySalesOrder(SalesOrder salesOrder);

    void signedTask(User user, Task task);

    void unsignedTask(User user, Task task);

    List<Task> findTaskRootBy(SalesOrder salesOrder);


}
