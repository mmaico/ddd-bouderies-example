package com.crm.negotiation.infrastructure.persistence;



import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.helpers.businessmodel.BusinessModel;
import com.crm.infrastructure.helpers.businessmodel.reflections.ReflectionMirrorUtils;
import com.crm.negotiation.domain.model.customer.Customer;
import com.crm.negotiation.domain.model.negotiation.Negotiation;
import com.crm.negotiation.domain.model.seller.Seller;
import org.apache.commons.beanutils.PropertyUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestCopyBean {

  @Test
  public void should() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Negotiation negotiation = new Negotiation();
    negotiation.setId(1l);
    negotiation.setCareOf("EU");
    negotiation.setIntroduction("Introduction");

    Customer customer = new Customer();
    customer.setId(2l);

    Seller seller = new Seller();
    seller.setId(2l);

    negotiation.setCustomer(customer);
    negotiation.setSeller(seller);


    BusinessProposal businessProposal = BusinessModel.from(negotiation).convertTo(BusinessProposal.class);

    assertThat(businessProposal.getId(), Matchers.is(1l));
    assertThat(businessProposal.getClient().getId(), Matchers.is(2l));

  }
}
