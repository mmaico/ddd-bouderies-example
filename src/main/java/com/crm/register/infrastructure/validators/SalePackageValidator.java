package com.crm.register.infrastructure.validators;

import com.crm.infrastructure.entity.saleable.SalePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SalePackageValidator implements Validator {

    @Autowired
    private SaleableValidator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return SalePackage.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SalePackage salePackage = (SalePackage) target;

        validator.validate(salePackage, errors);

    }


}
