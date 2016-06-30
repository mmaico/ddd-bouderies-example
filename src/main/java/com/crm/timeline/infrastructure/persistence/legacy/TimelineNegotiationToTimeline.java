package com.crm.timeline.infrastructure.persistence.legacy;

import com.crm.infrastructure.entity.timeline.Timeline;
import com.crm.timeline.domain.model.negotiation.TimelineNegotiation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimelineNegotiationToTimeline implements Converter<TimelineNegotiation, Timeline> {


  @Override public Timeline convert(TimelineNegotiation source) {
    return new Timeline();
  }
}
