package com.crm.infrastructure.repository;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import com.crm.infrastructure.entity.notification.Notification;
import com.crm.infrastructure.entity.notification.TaskNotification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface NotificationRepository extends BaseRepository<Notification, Long> {

    @Query("SELECT abpn FROM ApprovalBusinessProposalNotification AS abpn WHERE abpn.notified = :user " +
            " ORDER BY abpn.createDate DESC")
    List<ApprovalBusinessProposalNotification> findProposalNotificationBy(@Param("user") User user);

    @Query("SELECT tn FROM TaskNotification AS tn WHERE tn.notified = :user " +
            " ORDER BY tn.createDate DESC")
    List<TaskNotification> findTaskNotificationBy(@Param("user") User user);

    @Query("SELECT count(tn) FROM TaskNotification AS tn WHERE tn.notified = :user AND tn.createDate > :date ")
    Long findCountTaskNotificationBy(@Param("user") User user, @Param("date") Date date);

    @Query("SELECT count(abpn) FROM ApprovalBusinessProposalNotification AS abpn " +
            " WHERE abpn.notified = :user AND abpn.createDate > :date ")
    Long findCountProposalBy(@Param("user") User user, @Param("date") Date date);

}
