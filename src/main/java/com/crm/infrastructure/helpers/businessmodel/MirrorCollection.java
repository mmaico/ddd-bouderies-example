package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.exceptions.InvalidCollectionException;
import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.PreviousNode;
import com.crm.infrastructure.helpers.businessmodel.node.TreeMirrorNode;
import com.google.common.collect.Iterables;

import java.util.Optional;

import static com.crm.infrastructure.helpers.businessmodel.node.DestinationNode.newDestNode;
import static com.crm.infrastructure.helpers.businessmodel.node.OriginNode.newOrigin;

public class MirrorCollection {


  public void mirror(OriginNode originNode, DestinationNode destinationNode) {

    if (!originNode.isClassCollection()) {
      InvalidCollectionException.throwingError(originNode);
    }

    if (!destinationNode.isClassCollection()) {
      //Ajustar mensagem da exception
      InvalidCollectionException.throwingError(originNode);
    }
    final Iterable destinationCollection;

    if (!destinationNode.collectionIsPresent()) {
        destinationCollection = destinationNode.generateNewCollection();
    } else {
        destinationCollection = (Iterable) destinationNode.getObject();
    }

    Iterable originCollection = (Iterable) originNode.getObject();

    for (Object itemOrigin: originCollection) {
      Object found = Iterables.find(destinationCollection, e -> e.equals(itemOrigin));

      if (found != null) {
        TreeMirrorNode initialTreeMirrorNode = TreeMirrorNode.newOrigNode(newOrigin(itemOrigin, null), newDestNode(found,
                Optional.empty(), PreviousNode.newPreviousNode(null, null)));

        new MirrorObject().mirror(initialTreeMirrorNode);

      }
    }


  }

}
