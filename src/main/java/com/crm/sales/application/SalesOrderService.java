package com.crm.sales.application;



import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;

import com.crm.sales.domain.model.sale.SalesOrder;
import com.crm.sales.domain.model.sale.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class SalesOrderService implements SalesOrderFacade {


  @Autowired
  private SalesRepository repository;

  @Override
  public SalesOrder generateUsing(NegotiationClosedWon negotiationClosedWon) {

    Optional<SalesOrder> result = repository.findBy(negotiationClosedWon);
    if (result.isPresent()) {
      hasErrors(newHashSet("already.exists.sales.order.to.domain"))
          .throwing(ValidationException.class);
    }
    //
    //        SalesOrder salesOrder = converter.convert(businessProposal);
    //        SalesOrder salesOrderSaved = super.save(salesOrder);
    //
    //        eventBus.post(NewSalesOrderMessage.create(salesOrderSaved));
    //
    //        return salesOrderSaved;

    return null;
  }



}
