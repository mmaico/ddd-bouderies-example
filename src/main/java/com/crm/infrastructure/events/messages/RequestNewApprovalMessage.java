package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.proposal.requestapproval.RequestApproval;

public class RequestNewApprovalMessage {


    private RequestApproval requestApproval;

    public RequestNewApprovalMessage(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }

    public static RequestNewApprovalMessage create(RequestApproval requestApproval) {
          return new RequestNewApprovalMessage(requestApproval);
    }

    public RequestApproval getRequestApproval() {
        return requestApproval;
    }

    public void setRequestApproval(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }
}
