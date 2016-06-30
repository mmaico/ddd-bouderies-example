package com.crm.delivery.infrastructure.helpers;


import com.crm.delivery.application.WorkspaceApplication;
import com.crm.delivery.application.tasks.TaskApplication;
import com.crm.delivery.application.tasks.UserWorkTaskApplication;
import com.crm.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import com.crm.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryDashboardHelper {

    @Autowired
    private TaskApplication application;

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private UserWorkTaskApplication userWorkTaskApplication;

    @Autowired
    private SecurityHelper security;

    public DeliveryResumeExecutionTaskDTO getResumeTasks() {
        return application.getResume();
    }

    public DeliveryResumeExecutionTaskDTO getResumeTasks(SalesOrder salesOrder) {
        return application.getResume(salesOrder);
    }

    public List<SalesOrder> findNewSalesOrder() {
        return workspaceApplication.findNewSalesOrder();
    }

    public List<SalesOrder> findSalesOrderInActDelivery() {
        return workspaceApplication.findSalesOrderNotInWorkspace();
    }

    public List<User> findUsersResponsibles(SalesOrder salesOrder) {
        return workspaceApplication.findUsersResponsibles(salesOrder);
    }

    public Boolean isInMyWorkspace(SalesOrder salesOrder) {
        return workspaceApplication.isInMyWorkspace(salesOrder, security.getPrincipal().getUser());
    }

    public Long countBySalesOrder(SalesOrder salesOrder) {
        return this.application.countBySalesOrder(salesOrder);
    }

    public List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting() {
        return userWorkTaskApplication.getSummaryTasksExecuting();
    }

    public List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrder salesOrder) {
        return userWorkTaskApplication.getSummarySalesOrderTasksExecuting(salesOrder);
    }

    public List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrder salesOrder) {
        return userWorkTaskApplication.getTaskExecutingHistory(salesOrder);
    }
}
