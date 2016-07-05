package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.PreviousNode;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ChildNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;
import static com.crm.infrastructure.helpers.businessmodel.node.PreviousNode.newPreviousNode;
import static com.crm.infrastructure.helpers.businessmodel.node.DestinationNode.newDestNode;
import static com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode.newOrigNode;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.getDestField;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.invokeGetter;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.mergePrimitiveAttributes;

public class MirrorObject {



  public void mirror(TreeMirrorNode treeMirrorNode) {
    final PreviousNode previousNode = treeMirrorNode.getPreviousNode();

    if (!treeMirrorNode.hasDestination()) {
      Optional<Object> newInstanceDestNode = treeMirrorNode.getOrigin().generateNewInstanceDestination();

      if (newInstanceDestNode.isPresent()) {
        ReflectionUtils.invokeSetter(previousNode.getObject(), previousNode.getField(), newInstanceDestNode.get());
        Optional<Field> destField = getDestField(newInstanceDestNode.get(), treeMirrorNode.getOrigin().getField());

        TreeMirrorNode nextTreeMirrorNode = newOrigNode(treeMirrorNode.getOrigin(), newDestNode(newInstanceDestNode.get(), destField, previousNode));

        mirror(nextTreeMirrorNode);
      }
    } else {
        mergePrimitiveAttributes(treeMirrorNode.getOrigin().getObject(), treeMirrorNode.getDest().getObject());
        List<ChildNode> children = ReflectionMirrorUtils.getReferenceFields(treeMirrorNode.getOrigin().getObject());

        for (ChildNode child : children) {
          OriginNode originNode = newOrigin(child.getObject(), child.getField());

          if (originNode.isNull()) return;

          Object destChild = invokeGetter(treeMirrorNode.getDest().getObject(), child.getField());

          PreviousNode previousNodeChild = newPreviousNode(treeMirrorNode.getDest().getObject(), originNode.getAttributeNameToDestination());
          DestinationNode destinationNode = newDestNode(destChild, getDestField(previousNode.getObject(), child.getField()), previousNodeChild);

          if (originNode.isClassCollection()) {
              new MirrorCollection().mirror(originNode, destinationNode);
          } else {
            TreeMirrorNode nextTreeMirrorNode = newOrigNode(originNode, destinationNode);

            mirror(nextTreeMirrorNode);
          }
        }
      }
    }

}
