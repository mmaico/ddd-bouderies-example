package com.crm.timeline.view;


import com.crm.infrastructure.events.messages.NegotiationClosedWonMessage;
import com.crm.timeline.application.TimelineActivitiesFacade;
import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.crm.timeline.domain.model.activity.TimelineActivityBuilder;
import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.NegotiationBuilder;
import com.crm.timeline.domain.model.user.UserInteracted;
import com.crm.timeline.domain.shared.LogActivityTypeEnum;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationActivitiesSubscriber {

    static final String MESSAGE = "Negociacao finalizada com sucesso :)";

    @Autowired
    private TimelineActivitiesFacade application;


    @Subscribe
    public void generateActivityByFinalizeRequestApproval(NegotiationClosedWonMessage message) {
      UserInteracted userInteracted = new UserInteracted(message.getUserId());
      Negotiation negotiation = NegotiationBuilder.createNegotiation(message.getNegotiationId()).build();

      TimelineActivity activity = TimelineActivityBuilder.createLogActivity()
              .withDescription(MESSAGE)
              .withType(LogActivityTypeEnum.FINALIZE_NEGOTIATION)
              .withUser(userInteracted).build();

      application.register(negotiation, activity);
    }

}
