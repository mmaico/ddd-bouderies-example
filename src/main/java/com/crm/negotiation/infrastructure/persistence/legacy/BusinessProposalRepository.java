package com.crm.negotiation.infrastructure.persistence.legacy;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessProposalRepository extends BaseRepository<BusinessProposal, Long> {


    List<BusinessProposal> findByClient(@Param("client") Person client);


}
