package com.crm.auditing.application;

import com.crm.auditing.domain.model.negotiation.NegotiationAudit;
import com.crm.auditing.domain.model.negotiation.NegotiationAuditRepository;
import com.crm.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class NegotiationAuditingService implements NegotiationAuditingFacade {

    @Autowired
    private NegotiationAuditRepository repository;

    public void register(NegotiationAudit negotiationAuditNew) {

        Page<NegotiationAudit> lasModitication = repository.findLasVersion(negotiationAuditNew.getEntityId(), Pager.build().withPageSize(1));

        if (lasModitication.getContent().size() == 0) {
            repository.save(negotiationAuditNew);
        } else {
            NegotiationAudit before = lasModitication.getContent().get(0);
            if (!before.getJson().equals(negotiationAuditNew.getJson())) {
                repository.save(negotiationAuditNew);
            }
        }

    }

    @Override
    public Page<NegotiationAudit> findLogs(Long entityId, Pager pager) {
        return repository.findAll(entityId, pager);
    }

}
