package com.crm.negotiation.application;


import com.crm.negotiation.domain.model.seller.Seller;
import com.crm.negotiation.domain.model.negotiation.*;
import com.crm.negotiation.domain.services.NegotiationRegisterBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationService implements NegotiationFacade {

  @Autowired
  private NegotiationRepository repository;

  @Autowired
  private NegotiationModifiedEvent negotiationModifiedEvent;

  @Autowired
  private NegotiationClosedWonEvent negotiationClosedWonEvent;


  @Override public Negotiation register(Negotiation negotiation) {

    NegotiationRegisterBusinessRules.negotiationFor(negotiation).isValidToRegister();

    negotiation.getSeller().hasPermissionToRegisterANegotiation();

    negotiationModifiedEvent.notifyModification(negotiation);

    return repository.save(negotiation);
  }

  @Override public void changeStatus(SellerChangeNegotiation sellerChangeNegotiation, NegotiationStatus newStatus) {
    Seller seller = sellerChangeNegotiation.getSeller();
    seller.hasPermissionToChangeStatusNegotiation();

    Negotiation negotiation = sellerChangeNegotiation.getNegotiation();
    Negotiation negotiationLoaded = repository.findOne(negotiation.getId());
    negotiationLoaded.changeStatusTo(newStatus);

    if (newStatus == NegotiationStatus.CLOSED_WON) {
      negotiationClosedWonEvent.closedOn(negotiationLoaded);
      negotiationModifiedEvent.notifyModification(negotiation);
    }
  }


}
