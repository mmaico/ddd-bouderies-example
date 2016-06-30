package com.crm.register.application.contract;

import com.crm.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import com.crm.infrastructure.service.ModelService;

import java.util.Optional;


public interface UserApproverProfileApplication extends ModelService<ApproverProfile> {

    Optional<ApproverProfile> register(ApproverProfile userProfile);

}
