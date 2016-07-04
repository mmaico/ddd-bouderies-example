package com.crm.infrastructure.helpers.businessmodel.node;


public class TreeMirrorNode {

  private final OriginNode origin;
  private final DestinationNode target;
  private final Root currentRoot;

  public TreeMirrorNode(OriginNode origin, DestinationNode target, Root root) {
    this.origin = origin;
    this.target = target;
    this.currentRoot = root;
  }

  public Boolean hasDestination() {
    return this.target.getObject() != null;
  }

  public OriginNode getOrigin() {
    return origin;
  }

  public DestinationNode getDest() {
    return target;
  }

  public Root getRoot() {
    return currentRoot;
  }

  public static TreeMirrorNode newOrigNode(OriginNode origin, DestinationNode target, Root root) {
    return new TreeMirrorNode(origin, target, root);
  }

}
