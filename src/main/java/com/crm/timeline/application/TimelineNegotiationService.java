package com.crm.timeline.application;

import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiationBuilder;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TimelineNegotiationService implements TimelineFacade {


  @Autowired
  private TimelineNegotiationRepository repository;


  @Override
  public TimelineNegotiation register(Negotiation negotiation) {

    Optional<TimelineNegotiation> result = repository.findBy(negotiation);

    if (result.isPresent()) {
      TimelineNegotiation timelineNegotiation = TimelineNegotiationBuilder.createTimeline(negotiation).build();
      return repository.save(timelineNegotiation);
    } else {
      return result.get();
    }

  }


}
