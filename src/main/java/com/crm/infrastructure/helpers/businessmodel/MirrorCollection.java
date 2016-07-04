package com.crm.infrastructure.helpers.businessmodel;


import com.crm.infrastructure.helpers.businessmodel.exceptions.InvalidCollectionException;
import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;

public class MirrorCollection {


  public void mirror(OriginNode originNode, DestinationNode destinationNode) {

    if (!originNode.isClassCollection()) {
      InvalidCollectionException.throwingError(originNode);
    }

    if (!destinationNode.isClassCollection()) {
      //Ajustar
      InvalidCollectionException.throwingError(originNode);
    }




  }

}
