package com.crm.infrastructure.helpers.businessmodel.node;


import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class DestinationNode {

    private final Object target;
    private final Optional<Field> field;
    private final PreviousNode previousNode;


    public DestinationNode(Object object, Optional<Field> field, PreviousNode previousNode) {
        this.target = object;
        this.field = field;
        this.previousNode = previousNode;
    }

    public Object getObject() {
        return target;
    }

    public static DestinationNode newDestNode(Object objectDest, Optional<Field> field, PreviousNode previousNode) {
        return new DestinationNode(objectDest, field, previousNode);
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

    public PreviousNode getPreviousNode() {
        return previousNode;
    }

    public boolean collectionIsPresent() {
        return target != null;
    }

    public Iterable generateNewCollection() {
        List list = new ArrayList<>();
        ReflectionUtils.invokeSetter(this.previousNode.getObject(), this.previousNode.getField(), list);
        return list;
    }

}
