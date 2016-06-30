package com.crm.timeline.application;

import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.crm.timeline.domain.model.negotiation.Negotiation;

public interface TimelineActivitiesFacade {


    void register(Negotiation negotiation, TimelineActivity item);

}
