package com.crm.infrastructure.helpers.businessmodel.node;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class DestinationNode {
    private final Object target;

    private final Optional<Field> field;


    public DestinationNode(Object object, Optional<Field> field) {
        this.target = object;
        this.field = field;
    }

    public Object getObject() {
        return target;
    }

    public static DestinationNode newDestNode(Object objectDest, Optional<Field> field) {
        return new DestinationNode(objectDest, field);
    }

    public Boolean isNull() {
        return target == null;
    }

    public boolean isClassCollection() {
        return  field.isPresent() && (Collection.class.isAssignableFrom(field.get().getType()) || Map.class.isAssignableFrom(field.get().getType()));
    }

    public Optional<Field> getField() {
        return field;
    }
}
