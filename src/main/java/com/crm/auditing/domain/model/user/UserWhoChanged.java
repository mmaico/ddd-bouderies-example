package com.crm.auditing.domain.model.user;

import com.crm.infrastructure.entity.Identifiable;


public class UserWhoChanged extends Identifiable {

  private Long id;


  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
