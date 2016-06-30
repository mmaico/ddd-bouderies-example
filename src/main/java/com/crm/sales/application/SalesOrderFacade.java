package com.crm.sales.application;




import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;
import com.crm.sales.domain.model.sale.SalesOrder;


public interface SalesOrderFacade {

    SalesOrder generateUsing(NegotiationClosedWon negotiationClosedWon);



}
