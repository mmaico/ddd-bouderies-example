package com.crm.negotiation.infrastructure.persistence.legacy;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepositoryNegotiation extends BaseRepository<Person, Long> {


    @Query("SELECT p FROM Person AS p WHERE p.id = :id")
    Optional<Person> getOne(@Param("id") Long id);

}
