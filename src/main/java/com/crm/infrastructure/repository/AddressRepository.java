package com.crm.infrastructure.repository;


import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.entity.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends BaseRepository<Address, Long> {

    @Query("SELECT a FROM Address AS a WHERE a.person = :person")
    List<Address> findByPerson(@Param("person") Person person);

}
