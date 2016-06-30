package com.crm.negotiation.domain.services;


import com.crm.infrastructure.configuration.ServiceLocator;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.negotiation.domain.model.customer.CustomerRepository;
import com.crm.negotiation.domain.model.negotiation.NegotiationItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;


public class NegotiationItemRegisterBusinessRules {


  private CustomerRepository repository;

  Map<String, CheckRule<NegotiationItem>> persistRules = new HashMap<>();
  {
    persistRules.put(RuleExpressionHelper.description("negotiation.item.has.product"), (ng) -> !ng.getProduct().isNew());
    persistRules.put(
        RuleExpressionHelper.description("negotication.item.has.price.greater.than.zero"), (ng) -> ng.getPrice().compareTo(BigDecimal.ZERO) > 0);
  }

  private final List<NegotiationItem> negotiationItems;

  public NegotiationItemRegisterBusinessRules(List<NegotiationItem> items) {
    this.negotiationItems = items;
    this.repository =  ServiceLocator.getBean(CustomerRepository.class);
  }

  public void isValidToRegister() {

    for (NegotiationItem item: negotiationItems) {
      Set<String> violations = persistRules.entrySet()
          .stream()
          .filter(e -> e.getValue().check(item))
          .map(Map.Entry::getKey).collect(Collectors.toSet());

      hasErrors(violations).throwing(ValidationException.class);
    }

  }


  public static NegotiationItemRegisterBusinessRules negotiationItemFor(List<NegotiationItem> items) {
      return new NegotiationItemRegisterBusinessRules(items);
  }
}
