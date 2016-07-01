package com.crm.infrastructure.helpers.businessmodel.node;


public class TreeMirrorNode {

  private final OriginNode origin;
  private final TargetNode target;
  private final Root currentRoot;

  public TreeMirrorNode(OriginNode origin, TargetNode target, Root root) {
    this.origin = origin;
    this.target = target;
    this.currentRoot = root;
  }

  public Boolean hasTarget() {
    return this.target.getObject() != null;
  }

  public OriginNode getOrigin() {
    return origin;
  }

  public TargetNode getTarget() {
    return target;
  }

  public Root getRoot() {
    return currentRoot;
  }

  public static TreeMirrorNode newNode(OriginNode origin, TargetNode target, Root root) {
    return new TreeMirrorNode(origin, target, root);
  }

}
