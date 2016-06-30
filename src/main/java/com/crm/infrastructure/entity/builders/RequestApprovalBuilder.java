package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.proposal.requestapproval.Approver;
import com.crm.infrastructure.entity.proposal.requestapproval.RequestApproval;

import java.util.List;

public class RequestApprovalBuilder extends AbstractBuilder<RequestApproval>  {

	public RequestApprovalBuilder() {
		this.entity = new RequestApproval();
	}

	public RequestApprovalBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public RequestApprovalBuilder withProposal(BusinessProposal proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

    public RequestApprovalBuilder withUserRequester(User userRequester) {
        this.entity.setUserRequester(userRequester);
        return this;
    }

    public RequestApprovalBuilder withStatus(RequestApproval.RequestApprovalStatus status) {
        this.entity.setStatus(status);
        return this;
    }

    public RequestApprovalBuilder withApprovers(List<Approver> approvers) {
        this.entity.setApprovers(approvers);
        return this;
    }


	public static RequestApprovalBuilder createRequestApproval(Long id) {
		return new RequestApprovalBuilder(id);
	}

	public static RequestApprovalBuilder createRequestApproval() {
		return new RequestApprovalBuilder();
	}
}
