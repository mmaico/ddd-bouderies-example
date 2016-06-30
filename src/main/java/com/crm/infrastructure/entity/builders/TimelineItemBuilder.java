package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.timeline.items.LogActivity;
import com.crm.infrastructure.entity.timeline.items.TimelineActivity;

import java.util.Date;
import java.util.List;

public class TimelineItemBuilder extends AbstractBuilder<TimelineActivity>  {

	public TimelineItemBuilder() {
		this.entity = new LogActivity();
	}

	public TimelineItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public TimelineItemBuilder withCreateDate(Date date) {
		this.entity.setCreation(date);
		return this;
	}

    public TimelineItemBuilder withDescription(String description) {
        this.entity.setDescription(description);
        return this;
    }

    public TimelineItemBuilder withFiles(List<AppFile> files) {
        this.entity.setFiles(files);
        return this;
    }

    public static TimelineItemBuilder createItem(Long id) {
		return new TimelineItemBuilder(id);
	}

	public static TimelineItemBuilder createItem() {
		return new TimelineItemBuilder();
	}
}
