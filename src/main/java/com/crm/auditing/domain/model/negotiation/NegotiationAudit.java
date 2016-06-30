package com.crm.auditing.domain.model.negotiation;


import com.crm.auditing.domain.model.user.UserWhoChanged;
import com.crm.infrastructure.entity.Identifiable;

import java.util.Date;

public class NegotiationAudit extends Identifiable {

  private Long id;

  private Long entityId;

  private Date creation;

  private String json;

  private UserWhoChanged user;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreation() {
    return creation;
  }

  public void setCreation(Date creation) {
    this.creation = creation;
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public Long getEntityId() {
    return entityId;
  }

  public void setEntityId(Long entityId) {
    this.entityId = entityId;
  }

  public UserWhoChanged getUser() {
    return user;
  }

  public void setUser(UserWhoChanged user) {
    this.user = user;
  }
}
