package com.crm.infrastructure.repository;

import com.crm.infra.AbstractIntegrationTest;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.crm.infrastructure.entity.builders.UserBuilder.createUser;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ActDeliverySalesRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private WorkspaceUnitRepository repository;

    @Test
    public void shouldReturnOnlyItemsInActDelivery() {
        List<SalesOrder> result = repository.findSalesOrderOutActDelivery();

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(3l));
        assertThat(result.get(1).getId(), is(4l));
    }

    @Test
    public void shouldReturnOnlyInActWithDistinctAndOrderingByDeliveryForecast() {
        List<SalesOrder> result = repository.findSalesOrderNotInWorkspace();

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(2l));
        assertThat(result.get(1).getId(), is(1l));
    }

    @Test
    public void shouldReturnAllUsersWithActDeliverySigned() {
        List<User> users = repository.findUsersWithSignedDelivery();

        assertThat(users.size(), is(2));

        assertThat(users.contains(createUser(1l).build()), Matchers.is(Boolean.TRUE));
        assertThat(users.contains(createUser(2l).build()), Matchers.is(Boolean.TRUE));
    }

}
