package com.crm.infrastructure.helpers.businessmodel.annotations;



import org.apache.commons.lang.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Reference {

  Class value();

  String fieldName() default StringUtils.EMPTY;
}
