package com.crm.timeline.infrastructure.persistence;

import com.crm.timeline.domain.model.activity.TimelineActivity;
import com.crm.timeline.domain.shared.TimelineActivitiesRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TimelineActivitiesRepositoryHibernate implements TimelineActivitiesRepository {

  @Override public TimelineActivity save(TimelineActivity activity) {
    return null;
  }
}
