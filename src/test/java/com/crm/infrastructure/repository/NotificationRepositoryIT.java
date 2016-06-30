package com.crm.infrastructure.repository;

import com.crm.infra.AbstractIntegrationTest;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.UserBuilder;
import com.crm.infrastructure.helpers.DateHelper;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.core.Is.is;


public class NotificationRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private NotificationRepository repository;

    @Test
    public void shouldCountTaskNotification() {
        User user = UserBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountTaskNotificationBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }

    @Test
    public void shouldCountProposalNotification() {
        User user = UserBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountProposalBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }
}
