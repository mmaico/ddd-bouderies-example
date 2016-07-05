package com.crm.negotiation.infrastructure.persistence;


import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.helpers.businessmodel.BusinessModel;
import com.crm.negotiation.domain.model.customer.Customer;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import com.crm.negotiation.domain.model.negotiation.NegotiationItem;
import com.crm.negotiation.domain.model.negotiation.NegotiationStatus;
import com.crm.negotiation.domain.model.seller.Seller;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestCopyBean {

  @Test
  public void should() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Negotiation negotiation = new Negotiation();
    negotiation.setId(1l);
    negotiation.setCareOf("EU");
    negotiation.setIntroduction("Introduction");
    negotiation.setStatus(NegotiationStatus.CLOSED_WON);

    Customer customer = new Customer();
    customer.setId(2l);

    Seller seller = new Seller();
    seller.setId(3l);

    negotiation.setCustomer(customer);
    negotiation.setSeller(seller);

    NegotiationItem itemOne = new NegotiationItem();
    itemOne.setId(10l);
    itemOne.setPrice(BigDecimal.TEN);

    negotiation.setItems(Lists.newArrayList(itemOne));

    BusinessProposal businessProposal = BusinessModel.from(negotiation).convertTo(BusinessProposal.class);

    assertThat(businessProposal.getId(), Matchers.is(1l));
    assertThat(businessProposal.getClient().getId(), Matchers.is(2l));
    assertThat(businessProposal.getSeller().getId(), Matchers.is(3l));
    assertThat(businessProposal.getSaleableItems().size(), Matchers.is(1));
    assertThat(businessProposal.getSaleableItems().get(0).getId(), Matchers.is(10l));
    assertThat(businessProposal.getSaleableItems().get(0).getPrice(), Matchers.is(BigDecimal.TEN));
  }
}
