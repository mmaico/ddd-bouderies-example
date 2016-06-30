package com.crm.delivery.application.subscribers;

import com.crm.delivery.application.tasks.TaskApplication;
import com.crm.infrastructure.events.messages.NewSalesOrderMessage;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateSalesOrderTasks {

    @Autowired
    private TaskApplication application;

    @Subscribe
    public void generateTaskBySalesOrder(NewSalesOrderMessage message) throws Exception {
        application.generateTaskByNewSalesOrder(message.getSalesOrder());
    }
}
