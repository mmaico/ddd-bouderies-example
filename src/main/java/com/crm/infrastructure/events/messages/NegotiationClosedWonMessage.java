package com.crm.infrastructure.events.messages;


public class NegotiationClosedWonMessage {

    private final String json;
    private final Long negotiationId;
    private final Long userId;

    public NegotiationClosedWonMessage(String negotiationJson, Long negotiationId, Long userId) {
        this.json = negotiationJson;
        this.negotiationId = negotiationId;
        this.userId = userId;
    }

    public static NegotiationClosedWonMessage create(String negotiationJson, Long negotiationId, Long userId) {
        return new NegotiationClosedWonMessage(negotiationJson, negotiationId, userId);
    }

    public String getNegotiation() {
        return json;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public Long getUserId() {
        return userId;
    }
}

