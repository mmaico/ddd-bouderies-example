package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.crm.infrastructure.helpers.businessmodel.exceptions.InvalidCollectionException;
import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.PreviousNode;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionUtils;
import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.DestinationNode.newDestNode;
import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;

public class MirrorCollection {


  public void mirror(OriginNode originNode, DestinationNode destinationNode) {

    if (!originNode.isClassCollection()) {
      InvalidCollectionException.throwingError(originNode);
    }

    if (!destinationNode.isClassCollection()) {
      InvalidCollectionException.throwingError(originNode, destinationNode);
    }

    final Collection destinationCollection;

    if (!destinationNode.collectionIsPresent()) {
        destinationCollection = destinationNode.generateNewCollection();
    } else {
        destinationCollection = (Collection) destinationNode.getObject();
    }

    Collection originCollection = (Collection) originNode.getObject();

    for (Object itemOrigin: originCollection) {

      com.google.common.base.Optional optional = Iterables.tryFind(destinationCollection, e -> e.equals(itemOrigin));
      final Object found;

      if (!optional.isPresent()) {
        Reference annotation = originNode.getField().getAnnotation(Reference.class);
        found = ReflectionUtils.newInstance(annotation.value());
        destinationCollection.add(found);
      } else {
        found = optional.get();
      }

      TreeMirrorNode initialTreeMirrorNode = TreeMirrorNode.newOrigNode(newOrigin(itemOrigin, null),
            newDestNode(found, Optional.empty(), PreviousNode.newPreviousNode(null, null)));

      new MirrorObject().mirror(initialTreeMirrorNode);
    }

  }

}
