package com.crm.negotiation.application;


import com.crm.negotiation.domain.model.negotiation.Negotiation;
import com.crm.negotiation.domain.model.negotiation.NegotiationStatus;
import com.crm.negotiation.domain.model.negotiation.SellerChangeNegotiation;

public interface NegotiationFacade {

  Negotiation register(Negotiation negotiation);

  void changeStatus(SellerChangeNegotiation negotiation, NegotiationStatus newStatus);
}
