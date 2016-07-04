package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.Root;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ChildNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;
import static com.crm.infrastructure.helpers.businessmodel.node.Root.newRoot;
import static com.crm.infrastructure.helpers.businessmodel.node.DestinationNode.newDestNode;
import static com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode.newOrigNode;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.getDestField;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.invokeGetter;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.mergePrimitiveAttributes;

public class MirrorObject {



  public void mirror(TreeMirrorNode treeMirrorNode) {
    final Root root = treeMirrorNode.getRoot();

    if (!treeMirrorNode.hasDestination()) {
      Optional<Object> newInstanceDestNode = treeMirrorNode.getOrigin().generateNewInstanceDestination();

      if (newInstanceDestNode.isPresent()) {
        ReflectionUtils.invokeSetter(root.getObject(), root.getField(), newInstanceDestNode.get());
        Optional<Field> destField = getDestField(newInstanceDestNode.get(), treeMirrorNode.getOrigin().getField());

        TreeMirrorNode nextTreeMirrorNode = newOrigNode(treeMirrorNode.getOrigin(),
                newDestNode(newInstanceDestNode.get(), destField), root);

        mirror(nextTreeMirrorNode);
      }
    } else {
        mergePrimitiveAttributes(treeMirrorNode.getOrigin().getObject(), treeMirrorNode.getDest().getObject());
        List<ChildNode> children = ReflectionMirrorUtils.getReferenceFields(treeMirrorNode.getOrigin().getObject());

        for (ChildNode child : children) {
          OriginNode originNode = newOrigin(child.getObject(), child.getField());

          if (originNode.isNull()) return;
          Object destChild = invokeGetter(treeMirrorNode.getDest().getObject(), child.getField());
          DestinationNode destinationNode = newDestNode(destChild, getDestField(root.getObject(), child.getField()));

          if (originNode.isClassCollection()) {

          }

          TreeMirrorNode nextTreeMirrorNode = newOrigNode(originNode, destinationNode,
                  newRoot(treeMirrorNode.getDest().getObject(), originNode.getAttributeNameToDestination()));

          mirror(nextTreeMirrorNode);
        }
      }
    }

}
