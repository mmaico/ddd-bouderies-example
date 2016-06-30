package com.crm.infrastructure.repository.task;

import com.crm.infra.AbstractIntegrationTest;
import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.helpers.DateHelper;
import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;


public class ScheduleTriggerNotificationRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ScheduleTriggerNotificationRepository repository;


    @Test
    @Ignore("verificar funcao date no h2")
    public void shouldReturnOnlyTriggerByDateAndNotExecuted() {
        Date date = DateHelper.convertToDate("20/10/2020");

        List<ScheduleTriggerNotification> result = repository.findAllAvailableToday(date);

        MatcherAssert.assertThat(result.size(), is(2));
        MatcherAssert.assertThat(result.get(0).getId(), is(2l));
        MatcherAssert.assertThat(result.get(1).getId(), is(3l));
    }
}
