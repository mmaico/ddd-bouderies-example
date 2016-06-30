package com.crm.infrastructure.repository.task;


import com.crm.infrastructure.entity.task.ScheduleTriggerNotification;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleTriggerNotificationRepository extends BaseRepository<ScheduleTriggerNotification, Long> {


    @Query("SELECT stn FROM ScheduleTriggerNotification AS stn WHERE stn.executed is false " +
            " AND date(stn.triggerDate) = date(:date)")
    List<ScheduleTriggerNotification> findAllAvailableToday(@Param("date") Date date);
}
