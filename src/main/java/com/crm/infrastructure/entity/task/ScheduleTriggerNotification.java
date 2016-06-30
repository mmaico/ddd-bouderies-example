package com.crm.infrastructure.entity.task;

import com.crm.auditing.infrastructure.ExcludeAuditingField;
import com.crm.infrastructure.entity.Identifiable;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="schedule_trigger_notification")
public class ScheduleTriggerNotification extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="trigger_date")
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date triggerDate;

    @ManyToOne
    @JoinColumn(name="task_id")
    @ExcludeAuditingField
    private Task task;

    private Boolean executed = Boolean.FALSE;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    public Boolean isValidTrigger() {
        Date today = new Date();

        if (triggerDate == null) return Boolean.FALSE;

        return !triggerDate.before(today);
    }

    public Boolean isActive() {

        return !new DateTime(this.getTriggerDate()).isBeforeNow();

    }


}
