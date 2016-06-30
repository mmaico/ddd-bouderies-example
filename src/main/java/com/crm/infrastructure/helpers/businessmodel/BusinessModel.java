package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.Node;
import com.crm.infrastructure.helpers.businessmodel.node.Root;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

public class BusinessModel {

  private Object object;

  public BusinessModel(Object object) {
    this.object = object;
  }

  public static BusinessModel from(Object object) {
      return new BusinessModel(object);
  }

  public <T> T convertTo(Class<T> clazz) {
    Object target = ReflectionUtils.newInstance(clazz);
    Node initialNode = Node.newNode(object, target, Root.newRoot(null, null));

    new MirrorObject().mirror(initialNode);

    return (T)target;
  }
}
