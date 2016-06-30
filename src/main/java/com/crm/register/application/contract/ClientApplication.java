package com.crm.register.application.contract;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.service.ModelService;

public interface ClientApplication extends ModelService<Person> {

    Client register(Client client);
}
