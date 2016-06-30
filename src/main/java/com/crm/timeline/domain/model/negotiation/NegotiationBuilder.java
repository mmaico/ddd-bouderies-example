package com.crm.timeline.domain.model.negotiation;


import com.crm.infrastructure.entity.builders.AbstractBuilder;

public class NegotiationBuilder extends AbstractBuilder<Negotiation> {

  public NegotiationBuilder() {
    this.entity = new Negotiation();
  }

  public NegotiationBuilder(Long id) {
    this();
    this.entity.setId(id);
  }


  public static NegotiationBuilder createNegotiation(Long id) {
    return new NegotiationBuilder(id);
  }


}
