package com.crm.negotiation.domain.services;


import com.crm.infrastructure.configuration.ServiceLocator;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.negotiation.domain.model.customer.CustomerRepository;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.crm.infrastructure.helpers.RuleExpressionHelper.description;


public class NegotiationRegisterBusinessRules {

  private CustomerRepository repository;

  Map<String, CheckRule<Negotiation>> persistRules = new HashMap<>();
  {
    persistRules.put(description("negotiation.has.seller"), (ng) -> ng.getSeller().isNew());
    persistRules.put(description("negotication.has.valid.customer"), (ng) -> !repository.exists(ng.getCustomer().getId()));
    persistRules.put(description("negotication.has.items"), (tp) -> Boolean.FALSE);

  }

  private final Negotiation negotiation;

  public NegotiationRegisterBusinessRules(Negotiation negotiation) {
    this.negotiation = negotiation;
    this.repository = ServiceLocator.getBean(CustomerRepository.class);
  }

  public void isValidToRegister() {

    Set<String> violations = persistRules.entrySet()
        .stream()
        .filter(e -> e.getValue().check(negotiation))
        .map(Map.Entry::getKey).collect(Collectors.toSet());

    hasErrors(violations).throwing(ValidationException.class);
  }


  public static NegotiationRegisterBusinessRules negotiationFor(Negotiation negotiation) {
      return new NegotiationRegisterBusinessRules(negotiation);
  }
}
