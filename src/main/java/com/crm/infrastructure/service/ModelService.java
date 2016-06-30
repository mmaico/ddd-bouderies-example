package com.crm.infrastructure.service;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ModelService<T> {

    T save(T entity, DomainBusinessRules... checkRules);

    Iterable<T> findAll(Pageable pager);
    
    Optional<T> getOne(Long id);
}
