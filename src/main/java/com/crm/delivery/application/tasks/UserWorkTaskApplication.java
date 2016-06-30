package com.crm.delivery.application.tasks;


import com.crm.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import com.crm.infrastructure.entity.sale.SalesOrder;

import java.util.List;

public interface UserWorkTaskApplication  {


    List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting();

    List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrder salesOrder);

    List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrder salesOrder);


}
