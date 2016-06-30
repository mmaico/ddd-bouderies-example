package com.crm.sales.infrastructure.persistence.hibernate;

import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;
import com.crm.sales.domain.model.sale.SalesOrder;
import com.crm.sales.domain.model.sale.SalesRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SaleRepositoryHibernate implements SalesRepository {

  @Override public Optional<SalesOrder> findBy(NegotiationClosedWon negotiation) {
    return null;
  }
}
