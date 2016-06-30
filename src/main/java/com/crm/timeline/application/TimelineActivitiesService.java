package com.crm.timeline.application;



import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.crm.timeline.domain.model.negotiation.Negotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiationRepository;
import com.crm.timeline.domain.shared.TimelineActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimelineActivitiesService implements TimelineActivitiesFacade {

	
    @Autowired
    private TimelineFacade service;

    @Autowired
    private TimelineNegotiationRepository negotiationRepository;

    @Autowired
    private TimelineActivitiesRepository repository;


    @Override
    public void register(Negotiation negotiation, TimelineActivity activity) {

        TimelineActivity activitySaved = repository.save(activity);

        TimelineNegotiation timeline = service.register(negotiation);
        timeline.add(activitySaved);

        negotiationRepository.save(timeline);
    }


}
