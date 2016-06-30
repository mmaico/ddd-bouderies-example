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

import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.getProperty;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.getPropertyName;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils.setProperty;

public class MirrorObject {



  public void mirror(Node node) {
    if (!node.hasTarget()) {
      final Root root = node.getRoot();
      Object currentRootObject = root.getObject();
      Optional<Field> field = ReflectionUtils.getField(currentRootObject, root.getField(), Reference.class);

      if (field.isPresent()) {
        Object value = ReflectionMirrorUtils.newInstanceByReference(field.get());
        setProperty(root.getObject(), root.getField(), value);
        Node nextNode = Node.newNode(node.getBase(), value, root);
        mirror(nextNode);
      }
    } else {
      ReflectionMirrorUtils.mergePrimitiveProperties(node.getBase(), node.getTarget());
      List<FieldDescriptor> children = ReflectionMirrorUtils.getReferenceFields(node.getBase());

      for (FieldDescriptor child: children) {
        Object targetChild = getProperty(node.getTarget(), child.getField());
        String propertyName = getPropertyName(node.getBase(), child.getField());
        Node nextNode = Node.newNode(child.getObject(), targetChild, Root.newRoot(node.getTarget(), propertyName));
        mirror(nextNode);
      }
    }
  }

}
