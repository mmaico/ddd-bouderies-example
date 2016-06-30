package com.crm.register.application.contract;

import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.service.ModelService;

import java.util.List;


public interface ContactApplication extends ModelService<Contact> {

    Contact register(Contact contact);

    Contact register(Contact contact, Client client);

    Contact register(Contact contact, Provider provider);

    List<Contact> getContactsByClient(Client client);

    List<Contact> getContactsByProvider(Provider provider);
}
