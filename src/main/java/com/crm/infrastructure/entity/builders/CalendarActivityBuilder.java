package com.crm.infrastructure.entity.builders;

import com.crm.infrastructure.entity.assistants.calendar.CalendarActivity;


public class CalendarActivityBuilder extends AbstractBuilder<CalendarActivity> {

	private CalendarActivity calendarActivity = new CalendarActivity();

    public CalendarActivityBuilder() {}

    public CalendarActivityBuilder(Long id) {
        calendarActivity.setId(id);
    }




    public static CalendarActivityBuilder createCalendarActivity(Long id) {
        return new CalendarActivityBuilder(id);
    }
    public static CalendarActivityBuilder createCalendarActivity() {
        return new CalendarActivityBuilder();
    }

	public CalendarActivity build() {
		return this.calendarActivity;
	}


	
}
