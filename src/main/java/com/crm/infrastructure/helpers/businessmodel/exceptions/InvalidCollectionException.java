package com.crm.infrastructure.helpers.businessmodel.exceptions;

import com.crm.infrastructure.helpers.businessmodel.node.DestinationNode;
import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;


public class InvalidCollectionException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;



  public InvalidCollectionException(OriginNode originNode) {
    super("Could not find a collection in origin ["
        + originNode.getField().getName() + "] on target [" + originNode.getField().getType() + "]");
  }

  public InvalidCollectionException(OriginNode originNode, DestinationNode destinationNode) {
    super("Could not find a collection on destination [ " + destinationNode.getPreviousNode().getObject() + " ]"
        + " calling destination method [ "  + originNode.getAttributeNameToDestination() + " ]");
  }

  public static void throwingError(OriginNode node) {
    new InvalidCollectionException(node);
  }

  public static void throwingError(OriginNode originNode, DestinationNode destinationNode) {
    new InvalidCollectionException(originNode, destinationNode);
  }

}
