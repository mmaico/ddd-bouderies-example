package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.proposal.requestapproval.RequestApproval;

public class RequestApprovalFinalizeMessage {


    private RequestApproval requestApproval;

    public RequestApprovalFinalizeMessage(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }

    public static RequestApprovalFinalizeMessage create(RequestApproval requestApproval) {
          return new RequestApprovalFinalizeMessage(requestApproval);
    }

    public RequestApproval getRequestApproval() {
        return requestApproval;
    }

    public void setRequestApproval(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }
}
