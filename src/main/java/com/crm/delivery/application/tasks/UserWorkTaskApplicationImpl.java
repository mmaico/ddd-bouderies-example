package com.crm.delivery.application.tasks;

import com.crm.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import com.crm.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import com.crm.delivery.infrastructure.repository.SalesOrderDeliveryRepository;
import com.crm.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import com.crm.delivery.infrastructure.repository.UserWorkOnTasksRepository;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.helpers.DateHelper;
import com.crm.infrastructure.helpers.RangeDateDTO;
import com.crm.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.crm.infrastructure.entity.enums.TaskStatus.*;

@Service
public class UserWorkTaskApplicationImpl implements UserWorkTaskApplication {

    @Autowired
    private UserWorkOnTasksRepository repository;

    @Autowired
    private TaskChangeHistoryRepository taskChangeHistoryRepository;

    @Autowired
    private SalesOrderDeliveryRepository salesOrderDeliveryRepository;



    @Override
    public List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting() {

        List<User> users = repository.findUsersWorksInTasks();

        return users.stream()
                .map(user -> DeliverySummaryExecutingDTO.createSummary(user)
                        .addStatistic(DONE, repository.countOnAllTasksBy(user, DONE))
                        .addStatistic(PROBLEM, repository.countOnAllTasksBy(user, PROBLEM))
                        .addStatistic(WAITING, repository.countOnAllTasksBy(user, WAITING))
                        .addStatistic(STATED, repository.countOnAllTasksBy(user, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrder salesOrder) {

        List<User> users = repository.findUsersWorksOnSalesOrder(salesOrder);

        return users.stream().map(user -> SalesOrderSummaryExecutingDTO.createSummary(user)
                        .addStatistic(DONE, repository.countOnTaskBy(user, salesOrder, DONE))
                        .addStatistic(PROBLEM, repository.countOnTaskBy(user, salesOrder, PROBLEM))
                        .addStatistic(WAITING, repository.countOnTaskBy(user, salesOrder, WAITING))
                        .addStatistic(STATED, repository.countOnTaskBy(user, salesOrder, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrder salesOrder) {

        SalesOrder salesOrderLoaded = salesOrderDeliveryRepository.findOne(salesOrder.getId());

        Page<Date> startDateResult = taskChangeHistoryRepository.findDateFromNewestHistory(salesOrder, Pager.build().one());
        Page<Date> endDateResult = taskChangeHistoryRepository.findDateFromOldestHistory(salesOrder, Pager.build().one());


        Date startDate = startDateResult.getContent().size() > 0 ? startDateResult.getContent().get(0) : salesOrderLoaded.getCreationDate();
        Date endDate = endDateResult.getContent().size() > 0 ? endDateResult.getContent().get(0) : new Date();

        List<RangeDateDTO> rangeWeeks = DateHelper.getRangeWeeks(startDate, endDate);

        List<TaskExecutingHistoryDTO> listHistory = rangeWeeks.stream().map(rangeDate ->
                TaskExecutingHistoryDTO.createSummary(rangeDate)
                        .addStatistic(DONE, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), DONE))
                        .addStatistic(STATED, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), STATED))
                        .addStatistic(PROBLEM, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), PROBLEM))
                        .addStatistic(WAITING, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), WAITING))
        ).collect(Collectors.toList());

        return listHistory;
    }
}
