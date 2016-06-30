package com.crm.timeline.domain.shared;

import com.crm.timeline.domain.model.activity.TimelineActivity;

public interface TimelineActivitiesRepository {

  TimelineActivity save(TimelineActivity activity);

}
