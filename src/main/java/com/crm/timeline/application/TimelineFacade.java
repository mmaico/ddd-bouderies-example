package com.crm.timeline.application;

import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;

public interface TimelineFacade {

    TimelineNegotiation register(Negotiation entity);
}

