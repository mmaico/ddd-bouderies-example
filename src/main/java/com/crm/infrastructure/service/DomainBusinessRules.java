package com.crm.infrastructure.service;


import com.crm.infrastructure.entity.Identifiable;
import org.apache.commons.lang3.NotImplementedException;

public interface DomainBusinessRules<T extends Identifiable> {

    default void checkBusinessRulesFor(T entity){
        throw new NotImplementedException("Nessesario implementar o checkbusinessRulesFor");
    }

    default Boolean isValidBusinessRulesFor(T entity, Identifiable identifiable) {
        throw new NotImplementedException("Nessesario implementar o isValidBusinessRulesFor");
    }
}
