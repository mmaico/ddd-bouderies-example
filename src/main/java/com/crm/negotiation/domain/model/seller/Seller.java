package com.crm.negotiation.domain.model.seller;


import com.crm.infrastructure.configuration.ServiceLocator;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.negotiation.infrastructure.exceptions.RoleRequiredException;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

public class Seller extends Identifiable {

  private Long id;

  private List<Role> roles = Lists.newArrayList();

  public void hasPermissionToRegisterANegotiation() {
    SellerRepository sellerRepository = ServiceLocator.getBean(SellerRepository.class);
    Optional<Seller> sellerLoaded = sellerRepository.findOne(id);
    if (!sellerLoaded.get().hasRole(RoleType.REGISTER_NEGOTIATION)) {
        throw new RoleRequiredException("Seller not have REGISTER NEGOTIATION role");
    }
  }

  public void hasPermissionToChangeStatusNegotiation() {
    SellerRepository sellerRepository = ServiceLocator.getBean(SellerRepository.class);
    Optional<Seller> sellerLoaded = sellerRepository.findOne(id);
    if (!sellerLoaded.get().hasRole(RoleType.NEGOTIATION_CHANGE_STATUS)) {
      throw new RoleRequiredException("Seller has not CHANGE STATUS NEGOTIATION role");
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public Boolean hasRole(RoleType type) {
    return roles.stream().filter(role -> role.getTypes().contains(type)).count() > 0;
  }
}
