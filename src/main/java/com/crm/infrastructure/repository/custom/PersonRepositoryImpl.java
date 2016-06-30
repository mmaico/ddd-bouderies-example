package com.crm.infrastructure.repository.custom;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.QPerson;
import com.crm.infrastructure.repository.GenericCustomRepository;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    private GenericCustomRepository genericCustomRepository;

    @Override
    public Page<Person> findAll(Predicate predicate, Pageable page, OrderSpecifier[] orders) {
        return genericCustomRepository.findAll(QPerson.person, predicate, page, orders);
    }
}
