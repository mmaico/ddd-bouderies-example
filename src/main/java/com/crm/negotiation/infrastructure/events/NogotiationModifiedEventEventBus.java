package com.crm.negotiation.infrastructure.events;

import com.crm.infrastructure.events.messages.NegotiationClosedWonMessage;
import com.crm.infrastructure.events.messages.NegotiationModifiedMessage;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import com.crm.negotiation.domain.model.negotiation.NegotiationClosedWonEvent;
import com.crm.negotiation.domain.model.negotiation.NegotiationModifiedEvent;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NogotiationModifiedEventEventBus implements NegotiationModifiedEvent, NegotiationClosedWonEvent {

  @Autowired
  private EventBus eventBus;

  @Autowired
  private Gson gson;

  @Override public void notifyModification(Negotiation negotiation) {
    NegotiationModifiedMessage modifiedMessage =
        NegotiationModifiedMessage.create(gson.toJson(negotiation), negotiation.getId(), negotiation.getSeller().getId());

    eventBus.post(modifiedMessage);
  }

  @Override public void closedOn(Negotiation negotiation) {
    NegotiationClosedWonMessage closedWonMessage = NegotiationClosedWonMessage
        .create(gson.toJson(negotiation), negotiation.getId(), negotiation.getSeller().getId());

    eventBus.post(closedWonMessage);
  }
}
