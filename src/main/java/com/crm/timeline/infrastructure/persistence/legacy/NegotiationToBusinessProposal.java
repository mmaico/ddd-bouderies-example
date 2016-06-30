package com.crm.timeline.infrastructure.persistence.legacy;

import com.crm.infrastructure.entity.proposal.BusinessProposal;

import com.crm.timeline.domain.model.negotiation.Negotiation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterToNegotiation")
public class NegotiationToBusinessProposal implements Converter<Negotiation, BusinessProposal> {



  @Override public BusinessProposal convert(Negotiation source) {
    //TODO fazer conversao
    return new BusinessProposal();
  }
}
