package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.PreviousNode;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;
import static com.crm.infrastructure.helpers.businessmodel.node.DestinationNode.newDestNode;

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

    TreeMirrorNode initialTreeMirrorNode = TreeMirrorNode.newOrigNode(newOrigin(object, null), newDestNode(target,
        Optional.empty(), PreviousNode.newPreviousNode(null, null)));

    new MirrorObject().mirror(initialTreeMirrorNode);

    return (T) target;
  }
}
