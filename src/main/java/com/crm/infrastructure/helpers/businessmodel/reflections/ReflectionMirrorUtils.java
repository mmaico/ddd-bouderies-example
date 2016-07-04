package com.crm.infrastructure.helpers.businessmodel.reflections;


import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.AnnotationsIgnoredOnCopy.IGNORED_ON_COPY;
import static org.apache.commons.lang.StringUtils.isBlank;

public class ReflectionMirrorUtils {


  public static void mergePrimitiveAttributes(Object base, Object target) {
    List<Field> fields = ReflectionUtils.getFields(base, IGNORED_ON_COPY.getAnn());
    fields.stream()
          .forEach(field -> ReflectionUtils.invokeSetter(target, field, ReflectionUtils.invokeGetter(base, field)));
  }

  public static List<ChildNode> getReferenceFields(Object base) {
    return ReflectionUtils.getValues(base, Lists.newArrayList(Reference.class));
  }

  public static Object newInstanceByReference(Object origin) {
    Optional<Reference> annotation = Optional.ofNullable(origin.getClass().getAnnotation(Reference.class));

    if (annotation.isPresent()) {
      return ReflectionUtils.newInstance(annotation.get().value());
    }

    return null;
  }

  public static Object invokeGetter(Object object, Field field) {
    Reference annotation = field.getAnnotation(Reference.class);
    if (isBlank(annotation.fieldName())) {
      return ReflectionUtils.invokeGetter(object, field.getName());
    } else {
      return ReflectionUtils.invokeGetter(object, annotation.fieldName());
    }
  }

  public static Optional<Field> getDestField(Object objectDest, Field fieldOrigin) {
    Reference annotation = fieldOrigin.getAnnotation(Reference.class);
    if (objectDest == null) {
      return Optional.empty();
    }

    if (isBlank(annotation.fieldName())) {
      return ReflectionUtils.getField(objectDest, fieldOrigin.getName());
    } else {
      return ReflectionUtils.getField(objectDest, annotation.fieldName());
    }
  }

  public static String getPropertyName(Field field) {
    Reference annotation = field.getAnnotation(Reference.class);
    if (isBlank(annotation.fieldName())) {
      return field.getName();
    } else {
      return annotation.fieldName();
    }
  }
}
