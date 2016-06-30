package com.crm.infrastructure.helpers.businessmodel.reflections;


public class FieldDescriptor {

  private final String name;
  private final Object object;

  public FieldDescriptor(Object object, String name) {
    this.name = name;
    this.object = object;
  }

  public static FieldDescriptor createDescriptor(Object object, String fieldName) {
      return new FieldDescriptor(object, fieldName);
  }

  public String getName() {
    return name;
  }

  public Object getObject() {
    return object;
  }
}
