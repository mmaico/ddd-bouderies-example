package com.crm.infrastructure.entity.activities;

import com.crm.infrastructure.entity.Identifiable;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="personal_activity_checklists")
public class ActivityChecklist extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "personal.activity.checklist.name.invalid")
    private String name;

    @Column(name="is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name="personal_activity_id")
    @NotNull(message = "personal.activity.checklist.task.null")
    private PersonalActivity activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public PersonalActivity getActivity() {
        return activity;
    }

    public void setActivity(PersonalActivity activity) {
        this.activity = activity;
    }
}
