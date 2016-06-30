package com.crm.delivery.infrastructure.generatebysalesorder.convert;

import com.crm.infrastructure.entity.builders.ChecklistBuilder;
import com.crm.infrastructure.entity.task.Checklist;
import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.entity.task.Task;
import org.springframework.core.convert.converter.Converter;


public class ChecklistTemplateToChecklist implements Converter<ChecklistTemplate, Checklist> {

    private Task task;

    public ChecklistTemplateToChecklist(Task task) {
        this.task = task;
    }

    @Override
    public Checklist convert(ChecklistTemplate source) {
        return ChecklistBuilder.createChecklist()
                    .withDone(Boolean.FALSE)
                    .withName(source.getName())
                    .withTask(task).build();

    }

    public static ChecklistTemplateToChecklist create(Task task) {
        return new ChecklistTemplateToChecklist(task);
    }
}
