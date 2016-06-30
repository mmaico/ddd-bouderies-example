package com.crm.infrastructure.helpers;


import com.crm.infrastructure.entity.Identifiable;

public class ModelHelper {

    public static Boolean isNull(Identifiable entity) {
        return entity == null;
    }
}
