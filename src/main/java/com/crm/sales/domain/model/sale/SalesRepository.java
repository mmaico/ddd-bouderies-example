package com.crm.sales.domain.model.sale;


import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;

import java.util.Optional;

public interface SalesRepository {


    Optional<SalesOrder> findBy(NegotiationClosedWon negotiation);

}
