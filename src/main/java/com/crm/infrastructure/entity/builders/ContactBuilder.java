package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.Contact;

public class ContactBuilder extends AbstractBuilder<Contact>  {

	public ContactBuilder() {
		this.entity = new Contact();
	}

	public ContactBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static ContactBuilder createContact(Long id) {
		return new ContactBuilder(id);
	}

	public static ContactBuilder createContact() {
		return new ContactBuilder();
	}
}
