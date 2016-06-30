package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.activities.PersonalActivity;
import com.crm.infrastructure.entity.enums.PersonalAcvitityStatus;

public class PersonalActivityBuilder extends AbstractBuilder<PersonalActivity>  {

	public PersonalActivityBuilder() {
		this.entity = new PersonalActivity();
	}

	public PersonalActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public PersonalActivityBuilder withStatus(PersonalAcvitityStatus status) {
        this.entity.setStatus(status);
        return this;
    }

	public static PersonalActivityBuilder createActivity(Long id) {
		return new PersonalActivityBuilder(id);
	}

	public static PersonalActivityBuilder createActivity() {
		return new PersonalActivityBuilder();
	}
}
