package com.crm.delivery.view;

import com.crm.delivery.application.tasks.ChecklistApplication;
import com.crm.delivery.infrastructure.validators.ChecklistValidator;
import com.crm.infrastructure.entity.builders.ChecklistBuilder;
import com.crm.infrastructure.entity.task.Checklist;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.crm.infrastructure.entity.builders.ChecklistTemplateBuilder.createChecklistTemplateBuilder;
import static com.crm.infrastructure.entity.builders.TaskBuilder.createTaskBuilder;
import static com.crm.infrastructure.entity.builders.TaskTemplateBuilder.createTaskTemplateBuilder;
import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@RestController
public class TaskChecklistController {

    @Autowired
    private ChecklistApplication application;

    @Autowired
    private ChecklistValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    @RequestMapping(value = "/tasks/{taskId}/checklists/save", method = RequestMethod.POST)
    public  @ResponseBody Checklist save(@ModelAttribute Checklist checklist, @PathVariable Long taskId) {

        checklist.setTask(createTaskBuilder(taskId).build());
        hasContraintViolated(checklist, validator);
        normalizeEntityRequest.doNestedReference(checklist);

        Checklist result = application.register(checklist);

        return result;

    }

    @RequestMapping(value="/tasks/{taskId}/checklists/save")
    public @ResponseBody void update(@ModelAttribute Checklist checklist, @PathVariable Long taskId, Model model) {

        hasContraintViolated(checklist, validator);

        normalizeEntityRequest.addFieldsToUpdate(checklist);
        checklist.setTask(createTaskBuilder(taskId).build());
        application.register(checklist);
    }

    @RequestMapping(value="/tasks/{taskId}/checklists")
    public ModelAndView list(@RequestParam(defaultValue="checklist-task-table",required=false, value="template") String templateName,
                             @PathVariable Long taskId, Model model) {

        List<Checklist> result = application.findCheckListBy(createTaskBuilder(taskId).build());

        model.addAttribute("checklists", result);

        return new ModelAndView("/delivery/tasks/includes/" + templateName);

    }

    @RequestMapping(value="/tasks/checklists/{checkListId}", method= RequestMethod.PUT)
    public @ResponseBody void maskOk(@PathVariable Long checkListId) {
        application.completed(ChecklistBuilder.createChecklist(checkListId).build());
    }

}
