package com.crm.negotiation.domain.model.negotiation;

import com.crm.negotiation.domain.model.customer.Customer;

import java.util.List;


public interface NegotiationRepository {

  List<Negotiation> findBy(Customer customer);

  Negotiation save(Negotiation negotiation);

  Negotiation findOne(Long id);
}
