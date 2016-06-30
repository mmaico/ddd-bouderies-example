package com.crm.infrastructure.validators;

import com.crm.infrastructure.entity.Identifiable;

@FunctionalInterface
public interface CheckRule<T extends Identifiable> {

    Boolean check(T t);
}
