package com.crm.infrastructure.validators;


import com.crm.infrastructure.entity.Identifiable;

public class ModelValidatorUtils {

    public static Boolean hasId(Identifiable identifiable) {
        if (identifiable == null ) {
            return Boolean.FALSE;
        }

        return !identifiable.isNew();
    }
}
