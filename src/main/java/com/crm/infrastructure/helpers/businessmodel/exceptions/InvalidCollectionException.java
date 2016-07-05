package com.crm.infrastructure.helpers.businessmodel.exceptions;

import com.crm.infrastructure.helpers.businessmodel.node.OriginNode;
import com.crm.infrastructure.helpers.businessmodel.node.PreviousNode;


public class InvalidCollectionException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;



  public InvalidCollectionException(OriginNode originNode) {
    super("Could not find a collection in origin ["
        + originNode.getField().getName() + "] on target [" + originNode.getField().getType() + "]");
  }

  public InvalidCollectionException(OriginNode originNode, PreviousNode root) {
    super("Could not find a collection on destination [ " + root.getObject() + " ]"
        + " calling destination method [ "  + originNode.getAttributeNameToDestination() + " ]");
  }


  public static void throwingError(OriginNode node) {
    new InvalidCollectionException(node);
  }

  public static void throwingError(OriginNode originNode, PreviousNode root) {
    new InvalidCollectionException(originNode, root);
  }

}
