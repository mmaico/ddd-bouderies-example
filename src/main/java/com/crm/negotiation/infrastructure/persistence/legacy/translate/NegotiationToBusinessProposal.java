package com.crm.negotiation.infrastructure.persistence.legacy.translate;

import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("negotiationTobusinessproposal")
public class NegotiationToBusinessProposal implements Converter<Negotiation, BusinessProposal> {



  @Override public BusinessProposal convert(Negotiation source) {
    //TODO fazer conversao
    return new BusinessProposal();
  }
}
