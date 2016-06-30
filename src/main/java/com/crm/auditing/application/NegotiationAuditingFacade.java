package com.crm.auditing.application;

import com.crm.auditing.domain.model.negotiation.NegotiationAudit;
import com.crm.infrastructure.repository.Pager;
import org.springframework.data.domain.Page;


public interface NegotiationAuditingFacade  {

    void register(NegotiationAudit negotiationAudit);

    Page<NegotiationAudit> findLogs(Long entityId, Pager pager);


}
