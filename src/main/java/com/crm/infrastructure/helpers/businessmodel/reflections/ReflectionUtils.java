package com.crm.infrastructure.helpers.businessmodel.reflections;


import com.crm.infrastructure.helpers.businessmodel.reflections.registers.PrimitiveTypeFields;
import net.vidageek.mirror.dsl.Mirror;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.businessmodel.reflections.ChildNode.createDescriptor;


public class ReflectionUtils {

    private static final String GETTER_PREFIX = "get";

    public static List<Field> getFields(Object base) {
        return new Mirror().on(base.getClass())
                .reflectAll().fields()
                .matching(field -> PrimitiveTypeFields.getInstance().contains(field.getType()));

    }

    public static Optional<Field> getField(Object base, String fieldName, Class annotation) {
        return new Mirror().on(base.getClass())
                .reflectAll().fields()
                .matching(field -> field.getAnnotation(annotation) != null && field.getName().equals(fieldName))
                .stream().findFirst();
    }

    public static Optional<Field> getField(Object base, String fieldName) {
        return new Mirror().on(base.getClass())
            .reflectAll().fields()
            .matching(field -> field.getName().equals(fieldName))
            .stream().findFirst();
    }

    public static List<ChildNode> getValues(Object base, List<Class> withAnnotations) {

        return new Mirror().on(base.getClass())
                .reflectAll().fields()
                .matching(field ->
                        withAnnotations.stream()
                                .filter(ann -> field.getAnnotation(ann) != null).count() > 0)
                .stream().map(field -> createDescriptor(invokeGetter(base, field), field))
                .collect(Collectors.toList());
    }

    public static Object invokeGetter(Object target, Field field) {
        return invokeGetter(target, field.getName());
    }


    public static Object invokeGetter(Object target, String name) {

        String getterMethodName = name;
        if (!name.startsWith(GETTER_PREFIX)) {
            getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
        }
        Method method = org.springframework.util.ReflectionUtils.findMethod(target.getClass(), getterMethodName);
        if (method == null && !getterMethodName.equals(name)) {
            getterMethodName = name;
            method = org.springframework.util.ReflectionUtils.findMethod(target.getClass(), getterMethodName);
        }
        if (method == null) {
            throw new IllegalArgumentException("Could not find getter method [" + getterMethodName + "] on target ["
                    + target + "]");
        }

        org.springframework.util.ReflectionUtils.makeAccessible(method);
        return org.springframework.util.ReflectionUtils.invokeMethod(method, target);
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

    public static void invokeSetter(Object target, Field field, Object value) {
        invokeSetter(target, field.getName(), value);
    }

    public static void invokeSetter(Object target, String fieldName, Object value) {
        try {
            BeanUtils.setProperty(target, fieldName, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
