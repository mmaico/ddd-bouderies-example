package com.crm.auditing.view;


import com.crm.auditing.application.NegotiationAuditingFacade;
import com.crm.auditing.domain.model.negotiation.NegotiationAudit;
import com.crm.auditing.domain.model.negotiation.NegotiationAuditBuilder;
import com.crm.auditing.domain.model.user.UserWhoChanged;
import com.crm.auditing.domain.model.user.UserWhoChangedAuditBuilder;
import com.crm.infrastructure.events.messages.NegotiationModifiedMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditingSubscriber {

    @Autowired
    private NegotiationAuditingFacade application;


    @Subscribe
    public void negotiationModified(NegotiationModifiedMessage event) {
        UserWhoChanged whoChanged = UserWhoChangedAuditBuilder.create(event.getUserId()).build();

        NegotiationAudit audit = NegotiationAuditBuilder.createAuditing()
            .withEntityId(event.getNegotiationId())
            .withInfo(event.getJson())
            .withUserWhoChanged(whoChanged).build();

        application.register(audit);
    }





}
