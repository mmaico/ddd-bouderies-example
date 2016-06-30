package com.crm.delivery.domain;

import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.task.TaskTemplateRepository;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class ChecklistTemplateDomainServiceImpl implements ChecklistTemplateDomainService {

    @Autowired
    private TaskTemplateRepository taskTemplateRepository;

    Map<String, CheckRule<ChecklistTemplate>> persistRules = new HashMap<>();
    {
        persistRules.put(RuleExpressionHelper.description("checklist.template.name.empty"), (ct) -> StringUtils.isBlank(ct.getName()));
        persistRules.put(RuleExpressionHelper.description("checklist.template.invalid.task"), (ct) ->
                ct.getTaskTemplate() == null || ct.getTaskTemplate().isNew() ||
            taskTemplateRepository.findOne(ct.getTaskTemplate().getId()) == null);

    }

    @Override
    public void checkBusinessRulesFor(ChecklistTemplate checklistTemplate) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(checklistTemplate))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
