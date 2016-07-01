package com.crm.timeline.infrastructure;

import com.crm.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import com.crm.infrastructure.entity.timeline.items.LogActivity;
import com.crm.infrastructure.exceptions.ValidationException;

import com.google.common.collect.Sets;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Component
public class TimelineActivitiesValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    private Map<Class<?>, Validator> validatorMap = new HashMap<>();

    {
        validatorMap.put(LogActivity.class, new TimelineLogActivityValidator());
        validatorMap.put(BusinessProposalApprovalActivity.class, new TimelineBusinessProposalValidator());

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LogActivity.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Validator validator = validatorMap.get(target.getClass());

        if (validator == null) {
            throw new ValidationException(Sets.newHashSet("timeline.invalid.type"));
        }

        validator.validate(target, errors);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }

    private class TimelineLogActivityValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Boolean.FALSE;
        }

        @Override
        public void validate(Object target, Errors errors) {
            LogActivity activity = (LogActivity) target;

            Set<ConstraintViolation<Object>> constraints = validator.validate(activity);

            if (isEmpty(activity.getDescription())) {
                errors.rejectValue("logactivity.description", "logactivity.description.is.invalid");
            }

            constraints.forEach(error ->
                    errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));

        }
    }

    private class TimelineBusinessProposalValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Boolean.FALSE;
        }

        @Override
        public void validate(Object target, Errors errors) {
            BusinessProposalApprovalActivity activity = (BusinessProposalApprovalActivity) target;

            Set<ConstraintViolation<Object>> constraints = validator.validate(activity);


            constraints.forEach(error ->
                    errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));

        }
    }

}
