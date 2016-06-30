package com.crm.auditing.domain.model.negotiation;


import com.crm.infrastructure.repository.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NegotiationAuditRepository {

  Page<NegotiationAudit> findAll(Long id, Pager pager);

  Page<NegotiationAudit> findLasVersion(Long entityId, Pageable pageable);

  NegotiationAudit save(NegotiationAudit negotiationAudit);
}
