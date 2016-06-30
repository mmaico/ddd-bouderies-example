package com.crm.infrastructure.repository;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.notification.UserNotificationLogView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserNotificationLogViewRepository extends BaseRepository<UserNotificationLogView, Long> {

    @Query("SELECT view FROM UserNotificationLogView AS view WHERE view.user = :user AND view.typeLogView = :logType ORDER BY view.lastVisualization DESC")
    Page<UserNotificationLogView> findLastVisualization(@Param("user") User user, @Param("logType") UserNotificationLogView.TypeLogView logType, Pageable page);
}
