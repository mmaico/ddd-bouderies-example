package com.crm.delivery.infrastructure.generatebysalesorder.convert;

import com.crm.infrastructure.entity.builders.TaskBuilder;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.task.TaskTemplate;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

import static com.crm.infrastructure.helpers.CollectionsHelper.safeIterable;
import static com.crm.infrastructure.helpers.DateHelper.addDayToDate;


public class TaskTemplateToTask implements Converter<TaskTemplate, Task> {

    private SalesOrder salesOrder;

    public TaskTemplateToTask(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }


    @Override
    public Task convert(TaskTemplate source) {
        Integer quantityDays = source.getQuantityDaysTofinishAfertSignedContract();

        TaskBuilder builder = TaskBuilder.createTaskBuilder()
                .withDeadline(addDayToDate(quantityDays, new Date()))
                .withDescription(source.getDescription())
                .withStatus(TaskStatus.WAITING)
                .withTitle(source.getTitle())
                .withRegion(source.getRegion())
                .withSalesOrder(salesOrder);

        safeIterable(source.getChecklistTemplates()).forEach(checklist -> builder.addCheckList(
                ChecklistTemplateToChecklist.create(builder.build()).convert(checklist)
        ));

        safeIterable(source.getTasksCostsTemplates()).forEach(cost -> builder.addTaskCost(
                TaskCostTemplateToTaskCost.create(builder.build()).convert(cost)
            ));

        if (source.getTemplatesChilds() != null) {
            source.getTemplatesChilds().stream()
                    .filter(item -> item.getRegion().equals(salesOrder.getOperationRegion()))
                        .forEach(child -> builder.addChild(TaskTemplateToTask.create(salesOrder).convert(child)));
        }

        return builder.build();
    }

    public static TaskTemplateToTask create(SalesOrder salesOrder) {
        return new TaskTemplateToTask(salesOrder);
    }
}
