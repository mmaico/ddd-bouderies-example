package com.crm.register.infrastructure.helpers;

import com.crm.infrastructure.entity.person.Company;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.ClientApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientHelper {

    @Autowired
    private ClientApplication application;

    public Boolean isCompany(Person person) {

        return (person instanceof Company) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Iterable<Person> getAllClients() {
        return application.findAll(Pager.build().withPageSize(10000));
    }

}
