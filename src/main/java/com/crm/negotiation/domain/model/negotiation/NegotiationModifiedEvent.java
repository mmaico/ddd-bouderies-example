package com.crm.negotiation.domain.model.negotiation;


public interface NegotiationModifiedEvent {


  void notifyModification(Negotiation negotiation);

}
