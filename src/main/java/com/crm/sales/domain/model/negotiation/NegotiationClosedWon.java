package com.crm.sales.domain.model.negotiation;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.sales.domain.model.seller.Seller;


public class NegotiationClosedWon extends Identifiable {

  private Long id;

  private Seller seller;

  private Customer customer;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Seller getSeller() {
    return seller;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }



}
