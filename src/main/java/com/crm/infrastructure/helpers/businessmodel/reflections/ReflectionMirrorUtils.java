package com.crm.infrastructure.helpers.businessmodel.reflections;


import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

import static com.crm.infrastructure.helpers.businessmodel.AnnotationsIgnoredOnCopy.IGNORED_ON_COPY;
import static org.apache.commons.lang.StringUtils.isBlank;

public class ReflectionMirrorUtils {


  public static void mergePrimitiveProperties(Object base, Object target) {
    List<Field> fields = ReflectionUtils.getFields(base, IGNORED_ON_COPY.getAnn());
    fields.stream()
          .forEach(field -> ReflectionUtils.setProperty(target, field, ReflectionUtils.getProperty(base, field)));
  }

  public static List<FieldDescriptor> getReferenceFields(Object base) {
    return ReflectionUtils.getValues(base, Lists.newArrayList(Reference.class));
  }

  public static Object newInstanceByReference(Field field) {
    Class clazzReferenced = field.getAnnotation(Reference.class).value();
    return ReflectionUtils.newInstance(clazzReferenced);
  }

  public static Object getProperty(Object object, Field field) {
    Reference annotation = field.getAnnotation(Reference.class);
    if (isBlank(annotation.fieldName())) {
      return ReflectionUtils.getProperty(object, field.getName());
    } else {
      return ReflectionUtils.getProperty(object, annotation.fieldName());
    }
  }

  public static String getPropertyName(Object target, Field field) {
    Reference annotation = field.getAnnotation(Reference.class);
    if (isBlank(annotation.fieldName())) {
      return field.getName();
    } else {
      return annotation.fieldName();
    }
  }
}
