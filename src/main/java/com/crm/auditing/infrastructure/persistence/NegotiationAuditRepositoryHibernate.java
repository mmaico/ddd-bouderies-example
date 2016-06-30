package com.crm.auditing.infrastructure.persistence;


import com.crm.auditing.domain.model.negotiation.NegotiationAudit;
import com.crm.auditing.domain.model.negotiation.NegotiationAuditRepository;
import com.crm.auditing.infrastructure.persistence.legacy.BusinessProposalAuditingRepository;
import com.crm.infrastructure.entity.auditing.BusinessProposalAudinting;
import com.crm.infrastructure.helpers.HibernateProxyUtil;
import com.crm.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class NegotiationAuditRepositoryHibernate implements NegotiationAuditRepository {

  @Autowired
  private BusinessProposalAuditingRepository repository;

  @Override public Page<NegotiationAudit> findAll(Long id, Pager pager) {
    Page<BusinessProposalAudinting> result = repository.findAll(id, pager);
    return HibernateProxyUtil.add(NegotiationAudit.class, result);
  }

  @Override public Page<NegotiationAudit> findLasVersion(Long entityId, Pageable pageable) {
    Page<BusinessProposalAudinting> result = repository.findLasVersion(entityId, pageable);
    return HibernateProxyUtil.add(NegotiationAudit.class, result);
  }

  @Override public NegotiationAudit save(NegotiationAudit negotiationAudit) {
    return null;
  }
}
