package com.crm.infrastructure.repository;


import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends BaseRepository<Contact, Long> {


    @Query("SELECT c FROM Contact AS c WHERE c.person = :person")
    List<Contact> findByPerson(@Param("person") Person person);
}
