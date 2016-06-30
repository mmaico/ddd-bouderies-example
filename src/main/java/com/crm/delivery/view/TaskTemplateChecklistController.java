package com.crm.delivery.view;

import com.crm.delivery.application.tasktemplates.ChecklistTemplateApplication;
import com.crm.delivery.infrastructure.validators.ChecklistTemplateValidator;
import com.crm.infrastructure.entity.builders.TaskTemplateBuilder;
import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.crm.infrastructure.entity.builders.ChecklistTemplateBuilder.createChecklistTemplateBuilder;
import static com.crm.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static com.crm.infrastructure.entity.builders.TaskTemplateBuilder.createTaskTemplateBuilder;
import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class TaskTemplateChecklistController {

    @Autowired
    private ChecklistTemplateApplication service;

    @Autowired
    private ChecklistTemplateValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/saleables/task-template/{taskTemplateId}/checklist-template/save", method = RequestMethod.POST)
    public  @ResponseBody ChecklistTemplate save(@ModelAttribute ChecklistTemplate checklistTemplate, @PathVariable Long taskTemplateId) {

        checklistTemplate.setTaskTemplate(createTaskTemplateBuilder(taskTemplateId).build());
        hasContraintViolated(checklistTemplate, validator);
        normalizeEntityRequest.doNestedReference(checklistTemplate);

        ChecklistTemplate result = service.register(checklistTemplate);

        return result;

    }

    @RequestMapping(value="/saleables/task-template/{taskTemplateId}/checklist-template/save")
    public @ResponseBody void update(@ModelAttribute ChecklistTemplate checklistTemplate, @PathVariable Long taskTemplateId, Model model) {

        hasContraintViolated(checklistTemplate, validator);

        normalizeEntityRequest.addFieldsToUpdate(checklistTemplate);
        checklistTemplate.setTaskTemplate(createTaskTemplateBuilder(taskTemplateId).build());
        service.register(checklistTemplate);
    }

    @RequestMapping(value="/saleables/task-template/{taskTemplateId}/checklist-templates")
    public ModelAndView list(@PathVariable Long taskTemplateId, Model model) {

        List<ChecklistTemplate> result = service.findCheckListBy(TaskTemplateBuilder.createTaskTemplateBuilder(taskTemplateId).build());

        model.addAttribute("checklistTemplates", result);
        return new ModelAndView("/delivery/saleables/task-template/includes/checklist-template-table");

    }

    @RequestMapping(value="/saleables/task-template/checklist-templates/{checklistTemplateId}", method= RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Long checklistTemplateId) {
        service.delete(createChecklistTemplateBuilder(checklistTemplateId).build());
    }

}
