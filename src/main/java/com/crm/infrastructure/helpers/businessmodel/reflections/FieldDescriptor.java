package com.crm.infrastructure.helpers.businessmodel.reflections;


import java.lang.reflect.Field;

public class FieldDescriptor {

  private final Field field;
  private final Object object;

  public FieldDescriptor(Object object, Field field) {
    this.field = field;
    this.object = object;
  }

  public static FieldDescriptor createDescriptor(Object object, Field field) {
      return new FieldDescriptor(object, field);
  }

  public Field getField() {
    return field;
  }

  public Object getObject() {
    return object;
  }
}
