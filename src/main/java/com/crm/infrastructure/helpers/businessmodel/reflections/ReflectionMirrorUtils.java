package com.crm.infrastructure.helpers.businessmodel.reflections;


import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;

import static com.crm.infrastructure.helpers.businessmodel.AnnotationsIgnoredOnCopy.IGNORED_ON_COPY;

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
}
