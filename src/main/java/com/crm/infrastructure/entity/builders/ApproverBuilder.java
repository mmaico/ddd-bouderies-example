package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.enums.ApproverStatus;
import com.crm.infrastructure.entity.proposal.requestapproval.Approver;
import com.crm.infrastructure.entity.proposal.requestapproval.RequestApproval;

public class ApproverBuilder extends AbstractBuilder<Approver>  {

	public ApproverBuilder() {
		this.entity = new Approver();
	}

	public ApproverBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ApproverBuilder withProposal(RequestApproval request) {
        this.entity.setRequestApproval(request);
        return this;
    }

    public ApproverBuilder withStatus(ApproverStatus status) {
        this.entity.setStatus(status);
        return this;
    }

    public ApproverBuilder withApprover(User approver) {
        this.entity.setApprover(approver);
        return this;
    }

    public ApproverBuilder withDescription(String description) {
        this.entity.setDescription(description);
        return this;
    }


	public static ApproverBuilder createApprover(Long id) {
		return new ApproverBuilder(id);
	}

	public static ApproverBuilder createApprover() {
		return new ApproverBuilder();
	}
}
