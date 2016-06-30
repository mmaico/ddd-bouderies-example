package com.crm.timeline.domain.model.negotiation;


import com.crm.infrastructure.entity.Identifiable;
import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.google.common.collect.Lists;

import java.util.List;

public class TimelineNegotiation extends Identifiable {

  private Long id;

  private Negotiation negotiation;

  private List<TimelineActivity> activities;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Negotiation getNegotiation() {
    return negotiation;
  }

  public void setNegotiation(Negotiation negotiation) {
    this.negotiation = negotiation;
  }

  public List<TimelineActivity> getActivities() {
    return activities;
  }

  public void add(TimelineActivity timelineActivity) {
    if (activities == null) {
      this.activities = Lists.newArrayList();
    }
    this.activities.add(timelineActivity);
  }

  public void setActivities(
      List<TimelineActivity> activities) {
    this.activities = activities;
  }
}
