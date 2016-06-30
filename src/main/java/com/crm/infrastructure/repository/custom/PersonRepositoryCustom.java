package com.crm.infrastructure.repository.custom;


import com.crm.infrastructure.entity.person.Person;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepositoryCustom {

    Page<Person> findAll(Predicate predicate, Pageable page, OrderSpecifier<?>... orders);
}
