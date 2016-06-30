package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.crm.infrastructure.helpers.businessmodel.node.Node;
import com.crm.infrastructure.helpers.businessmodel.node.Root;
import com.crm.infrastructure.helpers.businessmodel.reflections.FieldDescriptor;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class MirrorObject {



  public void mirror(Node node) {
    if (!node.hasTarget()) {
      final Root root = node.getRoot();
      Object currentRootObject = root.getObject();
      Optional<Field> field = ReflectionUtils.getField(currentRootObject, root.getField(), Reference.class);

      if (field.isPresent()) {
        Object object = ReflectionMirrorUtils.newInstanceByReference(field.get());
        Node nextNode = Node.newNode(node.getBase(), object, root);
        mirror(nextNode);
      }
    } else {
      ReflectionMirrorUtils.mergePrimitiveProperties(node.getBase(), node.getTarget());
      List<FieldDescriptor> children = ReflectionMirrorUtils.getReferenceFields(node.getBase());

      for (FieldDescriptor child: children) {
        Object targetChild = ReflectionUtils.getProperty(node.getTarget(), child.getName());
        Node nextNode = Node.newNode(child.getObject(), targetChild, Root.newRoot(node.getTarget(), child.getName()));
        mirror(nextNode);
      }
    }
  }

}
