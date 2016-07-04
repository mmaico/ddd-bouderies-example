package com.crm.infrastructure.helpers.businessmodel.node;


import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.getPropertyName;

public class OriginNode {

    private final Field field;
    private final Object origin;

    public OriginNode(Field field, Object origin) {
        this.field = field;
        this.origin = origin;
    }

    public String getAttributeNameToDestination() {

        return getPropertyName(field);
    }

    public Optional<Object> generateNewInstanceDestination() {
        return Optional.ofNullable(ReflectionMirrorUtils.newInstanceByReference(origin));
    }

    public boolean isClassCollection() {
        return  field != null  && (Collection.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType()));
    }

    public Object getObject() {
        return origin;
    }

    public Field getField() {
        return field;
    }

    public static OriginNode newOrigin(Object object, Field field) {
        return new OriginNode(field, object);
    }

    public boolean isNull() {
        return getObject() == null;
    }
}
