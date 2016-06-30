package com.crm.sales.view;

import com.crm.infrastructure.events.messages.NegotiationClosedWonMessage;
import com.crm.sales.application.SalesOrderFacade;
import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationSubscriber {

    @Autowired
    private SalesOrderFacade application;

    @Autowired
    private Gson gson;

    @Subscribe
    public void generateSalesOrder(NegotiationClosedWonMessage message) {
        NegotiationClosedWon negotiationClosedWon = gson.fromJson(message.getNegotiation(), NegotiationClosedWon.class);
        application.generateUsing(negotiationClosedWon);
    }
}
