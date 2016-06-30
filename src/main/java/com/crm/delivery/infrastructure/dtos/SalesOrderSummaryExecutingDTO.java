package com.crm.delivery.infrastructure.dtos;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.enums.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class SalesOrderSummaryExecutingDTO {

    private User user;
    Map<TaskStatus, Long> statistics = new HashMap<>();

    public SalesOrderSummaryExecutingDTO(User user) {
        this.user = user;
    }

    public SalesOrderSummaryExecutingDTO addStatistic(TaskStatus status, Long quantity) {
        statistics.put(status, quantity);
        return this;
    }

    public Long getCount(TaskStatus status) {
        Long result = statistics.get(status);

        return result == null ? 0l : result;
    }

    public User getUser() {
        return user;
    }

    public static SalesOrderSummaryExecutingDTO createSummary(User user) {
        return new SalesOrderSummaryExecutingDTO(user);
    }
}
