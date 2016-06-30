package com.crm.negotiation.infrastructure.persistence;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.helpers.HibernateProxyUtil;
import com.crm.infrastructure.repository.UserRepository;
import com.crm.negotiation.domain.model.seller.Seller;
import com.crm.negotiation.domain.model.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SellerRepositoryHibernate implements SellerRepository {

  @Autowired
  private UserRepository repository;

  @Override public boolean exists(Long id) {
    return repository.exists(id);
  }

  @Override public Optional<Seller> findOne(Long sallerId) {
    User user = repository.findOne(sallerId);
    return Optional.ofNullable(HibernateProxyUtil.add(Seller.class, user));
  }
}
