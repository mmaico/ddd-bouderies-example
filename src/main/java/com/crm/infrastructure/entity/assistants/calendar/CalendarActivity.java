package com.crm.infrastructure.entity.assistants.calendar;


import com.crm.auditing.infrastructure.ExcludeAuditingField;
import com.crm.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="calendar_activity")
public class CalendarActivity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name="activity_type_id")
    private ActivityType type;

    @ManyToOne
    @JoinColumn(name="calendar_id")
    @ExcludeAuditingField
    private Calendar calendar;

    @OneToOne(mappedBy = "calendarActivity", cascade = CascadeType.ALL)
    private Period period;

    private String location;

    public CalendarActivity(){}

    public CalendarActivity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
