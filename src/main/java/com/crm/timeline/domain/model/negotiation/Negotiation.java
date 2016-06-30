package com.crm.timeline.domain.model.negotiation;

import com.crm.infrastructure.entity.Identifiable;


public class Negotiation extends Identifiable {

  private Long id;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
