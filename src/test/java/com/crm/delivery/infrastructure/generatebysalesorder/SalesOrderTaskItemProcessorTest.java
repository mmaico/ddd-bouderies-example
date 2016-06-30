package com.crm.delivery.infrastructure.generatebysalesorder;

import com.crm.delivery.infrastructure.generatebysalesorder.convert.TaskTemplateToTask;
import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.entity.builders.SalesOrderBuilder;
import com.crm.infrastructure.entity.builders.SalesOrderItemBuilder;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.sale.SalesOrderItem;
import com.crm.infrastructure.entity.saleable.Product;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.repository.task.TaskTemplateRepository;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SalesOrderTaskItemProcessorTest {

    @InjectMocks
    private SalesOrderTaskItemProcessor processor;

    @Mock
    private TaskTemplateRepository repository;

    @Mock
    private TaskTemplateToTask taskTemplateToTask;

    @Before
    public void setUp() {
        this.processor = Mockito.spy(this.processor);
    }


    @Test
    public void shouldFindAllTasksBySalesOrder() throws Exception {
        SalesOrder salesOrder = getSalesOrderStub();
        List<Task> tasksMock = Lists.newArrayList(mock(Task.class), mock(Task.class), mock(Task.class));
        List<TaskTemplate> tasksOne = getTasksOne();
        List<TaskTemplate> tasksTwo = getTasksTwo();

        List<SalesOrderItem> salesOrderItems = salesOrder.getSalesOrderItems();

        given(repository.findTaskTemplateBy(salesOrderItems.get(0).getSaleableUnit(), salesOrder.getOperationRegion()))
                    .willReturn(tasksOne);

        given(repository.findTaskTemplateBy(salesOrderItems.get(1).getSaleableUnit(), salesOrder.getOperationRegion()))
                .willReturn(tasksTwo);

        given(processor.getConverter(salesOrder)).willReturn(taskTemplateToTask);

        given(taskTemplateToTask.convert(tasksOne.get(0))).willReturn(tasksMock.get(0));
        given(taskTemplateToTask.convert(tasksTwo.get(0))).willReturn(tasksMock.get(1));
        given(taskTemplateToTask.convert(tasksTwo.get(1))).willReturn(tasksMock.get(2));

        List<Task> result = processor.process(salesOrder);

        assertThat(result.size(), Matchers.is(3));
        assertThat(result.contains(tasksMock.get(0)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(1)), Matchers.is(Boolean.TRUE));
        assertThat(result.contains(tasksMock.get(2)), Matchers.is(Boolean.TRUE));
    }

    private SalesOrder getSalesOrderStub() {
        SalesOrderItem itemOne = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new Product(1l)).build();

        SalesOrderItem itemTwo = SalesOrderItemBuilder.createSalesOrderItem(1l)
                .withSaleable(new Product(2l)).build();

        List<SalesOrderItem> items = Lists.newArrayList(itemOne, itemTwo);
        return SalesOrderBuilder.createSalesOrder(10l)
                .withOperationRegion(new OperationRegion(1l))
                .withSalesOrderItems(items).build();


    }

    private List<TaskTemplate> getTasksTwo() {
        TaskTemplate templateOne = new TaskTemplate();
        templateOne.setId(1l);

        TaskTemplate templateTwo = new TaskTemplate();
        templateTwo.setId(2l);

        return Lists.newArrayList(templateOne, templateTwo);
    }

    private List<TaskTemplate> getTasksOne() {
        TaskTemplate templateOne = new TaskTemplate();
        templateOne.setId(33l);

        return Lists.newArrayList(templateOne);
    }

}
