package com.crm.delivery.infrastructure.generatebysalesorder;

import com.crm.delivery.infrastructure.generatebysalesorder.convert.TaskTemplateToTask;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.task.TaskTemplateRepository;
import com.google.common.collect.Lists;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesOrderTaskItemProcessor implements ItemProcessor<SalesOrder, List<Task>> {

    @Autowired
    private TaskTemplateRepository repository;

    @Override
    public List<Task> process(SalesOrder salesOrder) throws Exception {

        List<List<Task>> collect = salesOrder.getSalesOrderItems()
                .stream()
                .map(item -> repository.findTaskTemplateBy(item.getSaleableAvailable(), salesOrder.getOperationRegion())
                                .stream().map(template -> getConverter(salesOrder).convert(template))
                                .collect(Collectors.toList())
                ).collect(Collectors.toList());

        List<Task> allTasks = Lists.newArrayList();
        collect.stream().forEach(taskList -> allTasks.addAll(taskList));

        return allTasks;
    }

    protected TaskTemplateToTask getConverter(SalesOrder salesOrder) {
        return TaskTemplateToTask.create(salesOrder);
    }
}
