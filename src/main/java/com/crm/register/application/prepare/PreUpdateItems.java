package com.crm.register.application.prepare;


import com.crm.infrastructure.entity.proposal.BusinessProposal;

public interface PreUpdateItems {

    void preUpdate(BusinessProposal proposal);
}
