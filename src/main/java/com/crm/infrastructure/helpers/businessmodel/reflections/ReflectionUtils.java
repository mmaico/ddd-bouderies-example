package com.crm.infrastructure.helpers.businessmodel.reflections;


import net.vidageek.mirror.dsl.Mirror;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.businessmodel.reflections.FieldDescriptor.createDescriptor;


public class ReflectionUtils {

  public static List<Field> getFields(Object base, List<Class> ignoreFieldsWithAnn) {
    return new Mirror().on(base.getClass())
        .reflectAll().fields()
        .matching(field ->
            ignoreFieldsWithAnn.stream()
                .filter(ann -> field.getAnnotation(ann) != null).count() == 0);

  }

  public static Optional<Field> getField(Object base, String fieldName, Class annotation) {
    return new Mirror().on(base.getClass())
        .reflectAll().fields()
        .matching(field -> field.getAnnotation(annotation) != null && field.getName().equals(fieldName))
        .stream().findFirst();
  }

  public static List<FieldDescriptor> getValues(Object base, List<Class> withAnnotations) {

    return new Mirror().on(base.getClass())
        .reflectAll().fields()
        .matching(field ->
            withAnnotations.stream()
                .filter(ann -> field.getAnnotation(ann) != null).count() > 0)
        .stream().map(field -> createDescriptor(getProperty(base, field), field))
        .collect(Collectors.toList());
  }

  public static Object getProperty(Object target, Field field) {
      return getProperty(target, field.getName());
  }

  public static Object getProperty(Object target, String field) {
    try {
      return BeanUtils.getProperty(target, field);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Object newInstance(Class clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void setProperty(Object target, Field field, Object value) {
    setProperty(target, field.getName(), value);
  }

  public static void setProperty(Object target, String fieldName, Object value) {
    try {
      BeanUtils.setProperty(target, fieldName, value);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

}
