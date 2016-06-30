package com.crm.infrastructure.helpers;


import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

public class HibernateProxyUtil {



  public static <T, E> List<T> add(Class<T> clazz, List<E> list) {
    return Lists.newArrayList();
  }

  public static <T, E> Page<T> add(Class<T> clazz, Page<E> page) {
    return new PageImpl<T>(Lists.newArrayList());
  }

  public static <T, E> T add(Class<T> clazz, E entity) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T, E> Optional<T> add(Class<T> clazz, Optional<E> entity) {
    try {
      if (entity.isPresent()) {
        return Optional.ofNullable(clazz.newInstance());
      } else {
        return Optional.empty();
      }
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
