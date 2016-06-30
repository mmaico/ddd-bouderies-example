package com.crm.infrastructure.entity.task;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.enums.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="task_change_status_history")
public class TaskChangeHistory extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="task_id")
    @NotNull(message = "checklist.task.null")
    private Task taskChanged;

    @Enumerated(EnumType.STRING)
    @Column(name="status_changed")
    private TaskStatus statusChanged;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfChange;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTaskChanged() {
        return taskChanged;
    }

    public void setTaskChanged(Task taskChanged) {
        this.taskChanged = taskChanged;
    }

    public TaskStatus getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(TaskStatus statusChanged) {
        this.statusChanged = statusChanged;
    }

    public Date getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(Date dateOfChange) {
        this.dateOfChange = dateOfChange;
    }
}
