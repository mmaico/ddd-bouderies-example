package com.crm.negotiation.domain.model.product;


import com.crm.infrastructure.entity.Identifiable;

public class Product extends Identifiable {

  private Long id;

  private String name;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
