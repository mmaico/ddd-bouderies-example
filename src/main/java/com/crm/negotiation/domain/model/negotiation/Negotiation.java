package com.crm.negotiation.domain.model.negotiation;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.proposal.ProposalSaleableItem;
import com.crm.infrastructure.helpers.businessmodel.annotations.EntityReference;
import com.crm.negotiation.domain.model.customer.Customer;
import com.crm.negotiation.domain.model.seller.Seller;

import java.util.List;

@EntityReference(BusinessProposal.class)
public class Negotiation extends Identifiable {

  private Long id;

  private String introduction;

  private String careOf;

  @EntityReference(User.class)
  private Seller seller;

  @EntityReference(value = Person.class, fieldName = "client")
  private Customer customer;

  @EntityReference(value = ProposalSaleableItem.class, fieldName = "saleableItems")
  private List<NegotiationItem> items;


  private NegotiationStatus status;


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

  public List<NegotiationItem> getItems() {
    return items;
  }

  public void setItems(List<NegotiationItem> items) {
    this.items = items;
  }

  public NegotiationStatus getStatus() {
    return status;
  }

  public void setStatus(NegotiationStatus status) {
    this.status = status;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getCareOf() {
    return careOf;
  }

  public void setCareOf(String careOf) {
    this.careOf = careOf;
  }

  public void changeStatusTo(NegotiationStatus newStatus) {
    if (!this.getStatus().equals(newStatus)) {
        this.setStatus(newStatus);
    }
  }
}
