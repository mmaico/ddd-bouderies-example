package com.crm.timeline.infrastructure.persistence.legacy;

import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.timeline.Timeline;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TimelineRepository extends BaseRepository<Timeline, Long> {


    @Query("SELECT t FROM Timeline AS t where t.proposal = :proposal")
    Optional<Timeline> findOne(@Param("proposal") BusinessProposal proposal);

}
