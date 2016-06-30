package com.crm.infrastructure.service;


import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.helpers.BeanUtils;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Arrays.asList;

@Transactional
@Service
public abstract class BaseModelServiceImpl<T extends Identifiable> implements ModelService<T> {

    @Override
    public T save(T entity, DomainBusinessRules... checkrules) {

        if (entity == null) {
            return entity;
        }

        if (entity.isNew()) {
            asList(checkrules).stream()
                    .forEach(check -> check.checkBusinessRulesFor(entity));
            return getRepository().save(entity);
        } else {
            T entityLoaded = getRepository().findOne(entity.getId());

            if (entityLoaded == null) {
                throw new IllegalArgumentException("entity.to.update.not.exist");
            }

            BeanUtils.create().copyProperties(entityLoaded, entity);

            asList(checkrules).stream()
                            .forEach(check -> check.checkBusinessRulesFor(entityLoaded));

            return getRepository().save(entityLoaded);
        }
    }

    public Iterable<T> findAll(Pageable pager) {
        return getRepository().findAll(pager);
    }
    
    public Optional<T> getOne(Long id) {
    	
    	if (id == null) {
    		return Optional.empty();
    	}
    	
    	T entity = getRepository().findOne(id);
    	
    	return Optional.ofNullable(entity);
    }

    public abstract BaseRepository<T, Long> getRepository();
}
