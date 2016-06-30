package com.crm.infrastructure.entity.builders;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.assistants.calendar.Calendar;


public class CalendarBuilder extends AbstractBuilder<Calendar> {

	private Calendar calendar = new Calendar();

    public CalendarBuilder() {}

    public CalendarBuilder(Long id) {
        calendar.setId(id);
    }

    public CalendarBuilder withUser(User user) {
		this.calendar.setUser(user);
		return this;
	}


    public static CalendarBuilder create(Long id) {
        return new CalendarBuilder(id);
    }
    public static CalendarBuilder create() {
        return new CalendarBuilder();
    }

	public Calendar build() {
		return this.calendar;
	}


	
}
