package com.crm.infrastructure.repository;

import com.crm.infra.AbstractIntegrationTest;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.UserBuilder;
import com.crm.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApproverProfileRepositoryIT extends AbstractIntegrationTest {


    @Autowired
    private ApproverProfileRepository repository;

    @Test
    public void shouldReturnTrueWheHasApproversAvailable() {

        Boolean result = repository.hasApprovers();

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnApproverProfileByUser() {
        User user = UserBuilder.createUser(2l).build();
        Optional<ApproverProfile> result = repository.findByApprover(user);

        assertThat(result.isPresent(), is(Boolean.TRUE));
    }

}
