package com.crm.negotiation.domain.model.customer;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.helpers.businessmodel.annotations.EntityReference;

@EntityReference(Person.class)
public class Customer {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
