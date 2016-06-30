package com.crm.delivery.domain;

import com.crm.delivery.infrastructure.validators.TaskTemplateValidator;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.RegionRepository;
import com.crm.infrastructure.repository.Saleable.SaleableUnitRepository;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class TaskTemplateDomainServiceImpl implements TaskTemplateDomainService {

    @Autowired
    private TaskTemplateValidator validator;

    @Autowired
    private SaleableUnitRepository saleableUnitRepository;

    @Autowired
    private RegionRepository regionRepository;

    Map<String, CheckRule<TaskTemplate>> persistRules = new HashMap<>();
    {
        persistRules.put(RuleExpressionHelper.description("task.template.verify.valid.saleableunit"), (tp) ->
                tp.getSaleable() == null || tp.getSaleable().isNew() || !saleableUnitRepository.exists(tp.getSaleable().getId()));
        persistRules.put(RuleExpressionHelper.description("task.template.verify.valid.region"), (tp) -> !regionRepository.exists(tp.getRegion().getId()));

        persistRules.put(RuleExpressionHelper.description("task.template.verify.validation.error"), (tp) -> hasContraintViolated(tp, validator));

    }

    @Override
    public void checkBusinessRulesFor(TaskTemplate taskTemplate) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(taskTemplate))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
