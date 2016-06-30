package com.crm.infrastructure.validators;

import com.crm.infrastructure.entity.Identifiable;

@FunctionalInterface
public interface CheckParamsRule<T extends Identifiable, Y extends Identifiable> {

    Boolean check(T t, Y y);
}
