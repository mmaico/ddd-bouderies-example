package com.crm.delivery.infrastructure.repository;


import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.task.TaskChangeHistory;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TaskChangeHistoryRepository extends BaseRepository<TaskChangeHistory, Long> {


    @Query("SELECT tch.dateOfChange FROM TaskChangeHistory AS tch JOIN tch.taskChanged AS task " +
            " WHERE task.salesOrder = :salesOrder  ORDER BY tch.dateOfChange DESC ")
    Page<Date> findDateFromOldestHistory(@Param("salesOrder") SalesOrder salesOrder, Pageable pageable);

    @Query("SELECT tch.dateOfChange FROM TaskChangeHistory AS tch JOIN tch.taskChanged AS task " +
            " WHERE task.salesOrder = :salesOrder ORDER BY tch.dateOfChange ASC ")
    Page<Date> findDateFromNewestHistory(@Param("salesOrder") SalesOrder salesOrder, Pageable pageable);

    @Query("SELECT COUNT(*) FROM TaskChangeHistory AS tch " +
            " WHERE tch.dateOfChange >= :startDate AND tch.dateOfChange <= :endDate " +
            " AND tch.statusChanged = :status")
    Long countHistoryByRangeDatesAndStatus(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("status")TaskStatus status);
}
