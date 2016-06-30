package com.crm.timeline.infrastructure.persistence;



import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.timeline.Timeline;
import com.crm.infrastructure.helpers.HibernateProxyUtil;
import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiationRepository;
import com.crm.timeline.infrastructure.persistence.legacy.NegotiationToBusinessProposal;
import com.crm.timeline.infrastructure.persistence.legacy.TimelineNegotiationToTimeline;
import com.crm.timeline.infrastructure.persistence.legacy.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TimelineNegotiationRepositoryHibernate implements TimelineNegotiationRepository {

  @Autowired
  private TimelineRepository repository;

  @Autowired
  @Qualifier("converterToNegotiation")
  private NegotiationToBusinessProposal negotiationToBusinessProposal;

  @Autowired
  private TimelineNegotiationToTimeline timelineNegotiationtoTimeline;


  @Override public Optional<TimelineNegotiation> findBy(Negotiation negotiation) {
    BusinessProposal proposal = negotiationToBusinessProposal.convert(negotiation);
    Optional<Timeline> timeline = repository.findOne(proposal);

    return HibernateProxyUtil.add(TimelineNegotiation.class, timeline);
  }

  @Override public TimelineNegotiation save(TimelineNegotiation timelineNegotiation) {
    Timeline timeline = timelineNegotiationtoTimeline.convert(timelineNegotiation);
    Timeline timelineSaved = repository.save(timeline);

    return HibernateProxyUtil.add(TimelineNegotiation.class, timelineSaved);
  }
}
