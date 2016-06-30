package com.crm.infrastructure.entity.assistants.calendar;

import com.crm.auditing.infrastructure.ExcludeAuditingField;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="calendars")
public class Calendar extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "calendar")
    private List<CalendarActivity> activities;

    @OneToOne(mappedBy = "calendar")
    @ExcludeAuditingField
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CalendarActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<CalendarActivity> activities) {
        this.activities = activities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
