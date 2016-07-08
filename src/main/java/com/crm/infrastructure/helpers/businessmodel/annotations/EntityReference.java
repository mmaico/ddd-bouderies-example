package com.crm.infrastructure.helpers.businessmodel.annotations;



import com.crm.infrastructure.helpers.businessmodel.converters.AttributeConverter;
import com.crm.infrastructure.helpers.businessmodel.converters.NullObjectConverter;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface EntityReference {

  Class value();

  String fieldName() default StringUtils.EMPTY;

  Class<? extends AttributeConverter> convert() default NullObjectConverter.class;
}
