package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.person.Person;

public class PersonBuilder extends AbstractBuilder<Person>  {

	public PersonBuilder() {
		this.entity = new Person();
	}

	public PersonBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	
	public static PersonBuilder createPerson(Long id) {
		return new PersonBuilder(id);
	}

	public static PersonBuilder create() {
		return new PersonBuilder();
	}
}
