package com.crm.timeline.domain.model.negotiation;


import com.crm.infrastructure.entity.builders.AbstractBuilder;
import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.google.common.collect.Lists;

import java.util.List;

public class TimelineNegotiationBuilder extends AbstractBuilder<TimelineNegotiation> {

  public TimelineNegotiationBuilder() {
    this.entity = new TimelineNegotiation();
  }

  public TimelineNegotiationBuilder(Long id) {
    this();
    this.entity.setId(id);
  }

  public TimelineNegotiationBuilder(Negotiation negotiation) {
    this();
    this.entity.setNegotiation(negotiation);
  }


  public TimelineNegotiationBuilder withItem(TimelineActivity item) {
    if (this.entity.getActivities() == null) {
      this.entity.setActivities(Lists.newArrayList());
    }

    this.entity.getActivities().add(item);
    return this;
  }

  public TimelineNegotiationBuilder withItems(List<TimelineActivity> items) {
    if (this.entity.getActivities() == null) {
      this.entity.setActivities(Lists.newArrayList());
    }

    this.entity.getActivities().addAll(items);
    return this;
  }

  public static TimelineNegotiationBuilder createTimeline(Long id) {
    return new TimelineNegotiationBuilder(id);
  }

  public static TimelineNegotiationBuilder createTimeline(Negotiation timeline) {
    return new TimelineNegotiationBuilder(timeline);
  }

}
