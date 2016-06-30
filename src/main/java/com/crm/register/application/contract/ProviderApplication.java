package com.crm.register.application.contract;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.service.ModelService;

public interface ProviderApplication extends ModelService<Person> {

    Provider register(Provider client);
}
