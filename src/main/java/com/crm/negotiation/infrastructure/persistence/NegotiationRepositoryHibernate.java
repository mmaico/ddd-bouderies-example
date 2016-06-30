package com.crm.negotiation.infrastructure.persistence;


import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.helpers.HibernateProxyUtil;
import com.crm.negotiation.domain.model.customer.Customer;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import com.crm.negotiation.domain.model.negotiation.NegotiationRepository;
import com.crm.negotiation.infrastructure.persistence.legacy.BusinessProposalRepository;
import com.crm.negotiation.infrastructure.persistence.legacy.translate.CustomerToPerson;
import com.crm.negotiation.infrastructure.persistence.legacy.translate.NegotiationToBusinessProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NegotiationRepositoryHibernate implements NegotiationRepository {

  @Autowired
  private BusinessProposalRepository repository;

  @Autowired
  private CustomerToPerson customerToPerson;

  @Autowired
  private NegotiationToBusinessProposal negotiationToBusinessProposal;

  @Override public List<Negotiation> findBy(Customer customer) {
    List<BusinessProposal> proposals = repository.findByClient(customerToPerson.convert(customer));
    return HibernateProxyUtil.add(Negotiation.class, proposals);
  }

  @Override public Negotiation save(Negotiation negotiation) {
    BusinessProposal businessProposal = repository.save(negotiationToBusinessProposal.convert(negotiation));
    return HibernateProxyUtil.add(Negotiation.class, businessProposal);
  }

  @Override public Negotiation findOne(Long id) {
    BusinessProposal businessProposal = repository.findOne(id);
    return HibernateProxyUtil.add(Negotiation.class, businessProposal);
  }
}
