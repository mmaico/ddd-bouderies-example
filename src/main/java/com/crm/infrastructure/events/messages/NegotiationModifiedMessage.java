package com.crm.infrastructure.events.messages;


public class NegotiationModifiedMessage {

    private final String json;
    private final Long negotiationId;
    private final Long userId;

    public NegotiationModifiedMessage(String negotiationJson, Long negotiationId, Long userId) {
        this.json = negotiationJson;
        this.negotiationId = negotiationId;
        this.userId = userId;
    }

    public static NegotiationModifiedMessage create(String negotiationJson, Long negotiationId, Long userId) {
        return new NegotiationModifiedMessage(negotiationJson, negotiationId, userId);
    }

    public String getJson() {
        return json;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public Long getUserId() {
        return userId;
    }
}

