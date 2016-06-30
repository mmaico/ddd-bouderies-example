package com.crm.timeline.domain.model.negotiation;

import java.util.Optional;

public interface TimelineNegotiationRepository {


    Optional<TimelineNegotiation> findBy(Negotiation negotiation);

    TimelineNegotiation save(TimelineNegotiation timelineNegotiation);

}
