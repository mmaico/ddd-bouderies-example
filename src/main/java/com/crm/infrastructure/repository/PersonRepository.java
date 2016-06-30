package com.crm.infrastructure.repository;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.repository.custom.PersonRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person, Long>, PersonRepositoryCustom {


    @Query("SELECT p FROM Person AS p WHERE p.id = :id")
    Optional<Person> getOne(@Param("id")Long id);

}
