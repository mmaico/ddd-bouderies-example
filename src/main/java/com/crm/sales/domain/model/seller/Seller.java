package com.crm.sales.domain.model.seller;

import com.crm.infrastructure.entity.Identifiable;


public class Seller extends Identifiable {

  private Long id;

  @Override public Long getId() {
    return id;
  }
}
