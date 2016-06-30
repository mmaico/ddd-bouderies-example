package com.crm.negotiation.domain.model.seller;

import com.crm.infrastructure.entity.Identifiable;

import java.util.Set;


public class Role extends Identifiable {

  private Long id;

  private Set<RoleType> types;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<RoleType> getTypes() {
    return types;
  }

  public void setTypes(Set<RoleType> types) {
    this.types = types;
  }
}
