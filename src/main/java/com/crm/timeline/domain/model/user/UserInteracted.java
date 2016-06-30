package com.crm.timeline.domain.model.user;


import com.crm.infrastructure.entity.Identifiable;

public class UserInteracted extends Identifiable {

  private Long id;

  public UserInteracted(Long id) {
    this.id = id;
  }

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
