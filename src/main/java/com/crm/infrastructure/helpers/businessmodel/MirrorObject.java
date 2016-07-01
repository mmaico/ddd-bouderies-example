package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.Root;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.FieldDescriptor;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;

import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;
import static com.crm.infrastructure.helpers.businessmodel.node.Root.newRoot;
import static com.crm.infrastructure.helpers.businessmodel.node.TargetNode.newTarget;
import static com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils.mergePrimitiveProperties;

public class MirrorObject {



  public void mirror(TreeMirrorNode treeMirrorNode) {

    if (!treeMirrorNode.hasTarget()) {
      final Root root = treeMirrorNode.getRoot();

      Optional<Object> newInstanceTargetNode = treeMirrorNode.getOrigin().generateNewInstanceTarget();

      if (newInstanceTargetNode.isPresent()) {
        ReflectionUtils.invokeSetter(root.getObject(), root.getField(), newInstanceTargetNode.get());
        TreeMirrorNode nextTreeMirrorNode = TreeMirrorNode.newNode(treeMirrorNode.getOrigin(),
                newTarget(newInstanceTargetNode.get()), root);

        mirror(nextTreeMirrorNode);
      }
    } else {
        mergePrimitiveProperties(treeMirrorNode.getOrigin().getObject(), treeMirrorNode.getTarget().getObject());
        List<FieldDescriptor> children = ReflectionMirrorUtils.getReferenceFields(treeMirrorNode.getOrigin().getObject());

        for (FieldDescriptor child : children) {
          OriginNode originNode = newOrigin(child.getObject(), child.getField());
          Object targetChild = ReflectionMirrorUtils.invokeGetter(treeMirrorNode.getTarget().getObject(), child.getField());

          TreeMirrorNode nextTreeMirrorNode = TreeMirrorNode.newNode(originNode, newTarget(targetChild),
                  newRoot(treeMirrorNode.getTarget().getObject(), originNode.getAttributeNameToTarget()));

          mirror(nextTreeMirrorNode);
        }
      }
    }

}
