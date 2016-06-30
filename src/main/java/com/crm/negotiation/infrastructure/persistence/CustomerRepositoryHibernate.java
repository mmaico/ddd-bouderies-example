package com.crm.negotiation.infrastructure.persistence;

import com.crm.negotiation.domain.model.customer.CustomerRepository;
import com.crm.negotiation.infrastructure.persistence.legacy.PersonRepositoryNegotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerRepositoryHibernate implements CustomerRepository {

  @Autowired
  private PersonRepositoryNegotiation repository;


  @Override public boolean exists(Long id) {
    return repository.exists(id);
  }

}
