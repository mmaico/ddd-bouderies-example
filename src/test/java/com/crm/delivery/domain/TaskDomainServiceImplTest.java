package com.crm.delivery.domain;

import com.crm.delivery.infrastructure.validators.TaskValidator;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.SalesOrderRepository;
import com.crm.infrastructure.repository.UserRepository;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TaskDomainServiceImplTest {

    @InjectMocks
    private TaskDomainServiceImpl taskDomainService;

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskValidator validator;

    @Rule
    public ExpectedException throwing = ExpectedException.none();


    @Test
    public void shouldNotErrosWhenTaskIsOK () throws ParseException {
        Task taskStub = getTaskStub();

        given(salesOrderRepository.exists(taskStub.getSalesOrder().getId())).willReturn(Boolean.TRUE);
        given(userRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userRepository.exists(3l)).willReturn(Boolean.TRUE);

        taskDomainService.checkBusinessRulesFor(taskStub);
    }

    @Test
    public void shouldThrowExceptionWhenSalesOrderNotExist () throws ParseException {
        Task taskStub = getTaskStub();
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskStub.getSalesOrder().getId())).willReturn(Boolean.FALSE);
        given(userRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userRepository.exists(3l)).willReturn(Boolean.TRUE);

        try {
            taskDomainService.checkBusinessRulesFor(taskStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.sales.order.valid"), is(Boolean.TRUE));
    }

    @Test
    public void shouldThrowExceptionWhenExistAUserWithoutId () throws ParseException {
        Task taskStub = getTaskStub();
        taskStub.getSignedBy().get(0).setId(null);
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskStub.getSalesOrder().getId())).willReturn(Boolean.TRUE);
        given(userRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userRepository.exists(3l)).willReturn(Boolean.TRUE);


        try {
            taskDomainService.checkBusinessRulesFor(taskStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.users.valid"), is(Boolean.TRUE));
    }

    @Test
    public void shouldThrowExceptionWhenExistAUserIsNull () throws ParseException {
        Task taskStub = getTaskStub();
        taskStub.getSignedBy().add(null);
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskStub.getSalesOrder().getId())).willReturn(Boolean.TRUE);


        try {
            taskDomainService.checkBusinessRulesFor(taskStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.users.valid"), is(Boolean.TRUE));
    }

    private Task getTaskStub () throws ParseException {
        List<User> users = Lists.<User>newArrayList(new User(2l), new User(3l));

        Task taskStub = new Task();
        SalesOrder order = new SalesOrder();
        order.setId(1l);
        taskStub.setSalesOrder(order);
        taskStub.setSignedBy(users);
        taskStub.setDeadline(DateUtils.parseDate("20/05/2030", "dd/MM/yyyy"));

        return taskStub;
    }

}
