package com.crm.infrastructure.entity.enums;

import com.crm.infrastructure.entity.person.Company;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class EntityNameTest {

    @Test
    public void shouldReturnEnumWhenFound() {

        EntityName entityName = EntityName.get(BusinessProposal.class);
        EntityName entityName1 = EntityName.get(Person.class);

        MatcherAssert.assertThat(entityName, is(EntityName.BUSINESS_PROPOSAL));
        MatcherAssert.assertThat(entityName1, is(EntityName.PERSON));
    }

    @Test
    public void shouldReturnEnumUsingSubClass() {

        EntityName entityName1 = EntityName.get(Company.class);

        MatcherAssert.assertThat(entityName1, is(EntityName.PERSON));
    }
}
