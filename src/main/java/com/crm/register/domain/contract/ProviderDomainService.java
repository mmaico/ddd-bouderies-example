package com.crm.register.domain.contract;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.service.DomainBusinessRules;

public interface ProviderDomainService extends DomainBusinessRules<Person> {



    void checkBusinessRulesFor(Person provider);
}
