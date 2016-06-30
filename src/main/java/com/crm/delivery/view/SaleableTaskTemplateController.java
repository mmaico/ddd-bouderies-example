package com.crm.delivery.view;

import com.crm.delivery.application.tasktemplates.TaskTemplateApplication;
import com.crm.delivery.infrastructure.validators.TaskTemplateValidator;
import com.crm.delivery.view.dtos.TaskTemplateDTO;
import com.crm.infrastructure.entity.builders.TaskTemplateBuilder;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.crm.infrastructure.entity.builders.SaleableUnitBuilder.createSaleableUnit;
import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class SaleableTaskTemplateController {

    @Autowired
    private TaskTemplateApplication service;

    @Autowired
    private TaskTemplateValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/saleables/{saleableId}/task-template/save", method = RequestMethod.POST)
    public  @ResponseBody TaskTemplateDTO save(@ModelAttribute TaskTemplate taskTemplate,  @PathVariable Long saleableId) {

        hasContraintViolated(taskTemplate, validator);

        normalizeEntityRequest.doNestedReference(taskTemplate);
        taskTemplate.setSaleable(createSaleableUnit(saleableId).build());
        TaskTemplate result = service.register(taskTemplate);

        return TaskTemplateDTO.build(result);

    }

    @RequestMapping(value = "/saleables/{saleableId}/task-template/save", method = RequestMethod.PUT)
    public @ResponseBody TaskTemplateDTO update(@ModelAttribute TaskTemplate taskTemplate,  @PathVariable Long saleableId) {

        normalizeEntityRequest.addFieldsToUpdate(taskTemplate);
        taskTemplate.setSaleable(createSaleableUnit(saleableId).build());
        TaskTemplate result = service.register(taskTemplate);

        return TaskTemplateDTO.build(result);
    }

    @RequestMapping(value = "/saleables/task-template/{taskTemplateId}", method = RequestMethod.DELETE)
    public @ResponseBody void remove(@PathVariable Long taskTemplateId) {

        service.remove(TaskTemplateBuilder.createTaskTemplateBuilder(taskTemplateId).build());

    }


    @RequestMapping(value="/saleables/{saleableId}/task-template/list")
    public ModelAndView list(@PathVariable Long saleableId, Model model) {

        Iterable<TaskTemplate> result = this.service.findTaskTemplateOnlyRootBy(createSaleableUnit(saleableId).build());

        model.addAttribute("taskTemplates", result);
        model.addAttribute("saleable", createSaleableUnit(saleableId).build());

        return new ModelAndView("/delivery/saleables/task-template/includes/tasks");
    }

    @RequestMapping(value="/saleables/task-template/{templateId}")
    public ModelAndView viewInfo(@PathVariable Long templateId, Model model) {

        Optional<TaskTemplate> result = this.service.getOne(templateId);

        model.addAttribute("saleable", result.isPresent() ? result.get().getSaleable() : null);
        model.addAttribute("taskTemplate", result.isPresent() ? result.get() : null);
        return new ModelAndView("/delivery/saleables/task-template/includes/task");
    }

    @RequestMapping(value="/saleables/{saleableId}/task-template/new")
    public ModelAndView newTaskTemplate(@PathVariable Long saleableId, Model model) {

        model.addAttribute("saleable", createSaleableUnit(saleableId).build());
        return new ModelAndView("/delivery/saleables/task-template/includes/add-task");
    }

}
