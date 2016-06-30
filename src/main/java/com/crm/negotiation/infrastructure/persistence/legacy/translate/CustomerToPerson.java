package com.crm.negotiation.infrastructure.persistence.legacy.translate;


import com.crm.infrastructure.entity.builders.PersonBuilder;
import com.crm.infrastructure.entity.person.Person;
import com.crm.negotiation.domain.model.customer.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToPerson implements Converter<Customer, Person> {

  @Override public Person convert(Customer source) {
    return PersonBuilder.createPerson(source.getId()).build();
  }
}
