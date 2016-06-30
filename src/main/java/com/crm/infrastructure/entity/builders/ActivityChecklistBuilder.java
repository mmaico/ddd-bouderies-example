package com.crm.infrastructure.entity.builders;

import com.crm.infrastructure.entity.activities.ActivityChecklist;


public class ActivityChecklistBuilder extends AbstractBuilder<ActivityChecklist> {

	private ActivityChecklist activityChecklist = new ActivityChecklist();

    public ActivityChecklistBuilder() {}

    public ActivityChecklistBuilder(Long id) {
        activityChecklist.setId(id);
    }



    public static ActivityChecklistBuilder createActivityChecklist(Long id) {
        return new ActivityChecklistBuilder(id);
    }


	public ActivityChecklist build() {
		return this.activityChecklist;
	}


	
}
