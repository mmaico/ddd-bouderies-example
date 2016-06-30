package com.crm.infrastructure.helpers.businessmodel.node;


public class Root {

  private final Object root;
  private final String field;


  public Root(Object root, String field) {
    this.root = root;
    this.field = field;
  }

  public static Root newRoot(Object root, String field) {
    return new Root(root, field);
  }

  public Object getObject() {
    return root;
  }

  public String getField() {
    return field;
  }
}
