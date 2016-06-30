package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.entity.task.TaskTemplate;

public class ChecklistTemplateBuilder extends AbstractBuilder<ChecklistTemplate>  {

	public ChecklistTemplateBuilder() {
		this.entity = new ChecklistTemplate();
	}

	public ChecklistTemplateBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ChecklistTemplateBuilder withName(String name) {
        this.entity.setName(name);
        return this;
    }



    public ChecklistTemplateBuilder withTaskTemplate(TaskTemplate tasktemplate) {
        this.entity.setTaskTemplate(tasktemplate);
        return this;
    }

	public static ChecklistTemplateBuilder createChecklistTemplateBuilder(Long id) {
		return new ChecklistTemplateBuilder(id);
	}

	public static ChecklistTemplateBuilder createChecklistTemplateBuilder() {
		return new ChecklistTemplateBuilder();
	}
}
