package com.crm.infrastructure.helpers.businessmodel.node;


public class Node {

  private final Object base;
  private final Object target;
  private final Root root;

  public Node(Object base, Object target, Root root) {
    this.base = base;
    this.target = target;
    this.root = root;
  }

  public Boolean hasTarget() {
    return this.target != null;
  }

  public Object getBase() {
    return base;
  }

  public Object getTarget() {
    return target;
  }

  public Root getRoot() {
    return root;
  }

  public static Node newNode(Object base, Object target, Root root) {
    return new Node(base, target, root);
  }

}
