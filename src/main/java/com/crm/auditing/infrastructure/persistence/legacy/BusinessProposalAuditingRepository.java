package com.crm.auditing.infrastructure.persistence.legacy;


import com.crm.infrastructure.entity.auditing.BusinessProposalAudinting;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessProposalAuditingRepository extends BaseRepository<BusinessProposalAudinting, Long> {



    @Query("SELECT bpa FROM BusinessProposalAudinting AS bpa WHERE bpa.entityId = :entityId ORDER BY bpa.lastUpdate desc ")
    Page<BusinessProposalAudinting> findLasVersion(@Param("entityId") Long entityId, Pageable pageable);

    @Query("SELECT bpa FROM BusinessProposalAudinting AS bpa WHERE bpa.entityId = :entityId ORDER BY bpa.lastUpdate desc ")
    Page<BusinessProposalAudinting> findAll(@Param("entityId") Long entityId, Pageable pageable);
}
