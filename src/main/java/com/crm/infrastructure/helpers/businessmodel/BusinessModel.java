package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.Root;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;
import static com.crm.infrastructure.helpers.businessmodel.node.TargetNode.newTarget;

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

    TreeMirrorNode initialTreeMirrorNode = TreeMirrorNode.newNode(newOrigin(object, null), newTarget(target), Root.newRoot(null, null));

    new MirrorObject().mirror(initialTreeMirrorNode);

    return (T) target;
  }
}
