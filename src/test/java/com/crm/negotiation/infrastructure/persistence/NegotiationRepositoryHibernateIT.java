package com.crm.negotiation.infrastructure.persistence;

import com.crm.infra.AbstractIntegrationTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;


public class NegotiationRepositoryHibernateIT extends AbstractIntegrationTest {

  @Autowired
  private NegotiationRepositoryHibernate repository;


  @Test
  public void should() {

    MatcherAssert.assertThat(Boolean.TRUE, is(Boolean.TRUE));
  }
}
