package com.crm.delivery.infrastructure.generatebysalesorder.convert;

import com.crm.infrastructure.entity.builders.TaskCostBuilder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.task.TaskCost;
import com.crm.infrastructure.entity.task.TaskCostTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;


public class TaskCostTemplateToTaskCost implements Converter<TaskCostTemplate, TaskCost> {

    private Task task;

    public TaskCostTemplateToTaskCost(Task task) {
        this.task = task;
    }

    @Override
    public TaskCost convert(TaskCostTemplate source) {
        return TaskCostBuilder.createTaskCost()
                .withCost(source.getCost())
                .withDescription(StringUtils.EMPTY)
                .withisInternal(source.getIsInternal())
                .withTask(task).build();

    }

    public static TaskCostTemplateToTaskCost create(Task task) {
        return new TaskCostTemplateToTaskCost(task);
    }
}
