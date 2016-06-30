package com.crm.infrastructure.entity.timeline;


import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.Incident;
import com.crm.infrastructure.entity.activities.PersonalActivity;
import com.crm.infrastructure.entity.campaigns.Campaign;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.entity.timeline.items.TimelineActivity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="timelines")
public class Timeline extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="timeline_id")
    private List<TimelineActivity> activities;

    @OneToOne
    @JoinColumn(name="person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposal proposal;

    @OneToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    @OneToOne
    @JoinColumn(name="task_id")
    private Task task;

    @OneToOne
    @JoinColumn(name="personal_activity_id")
    private PersonalActivity personalActivity;

    @OneToOne
    @JoinColumn(name="campaign_id")
    private Campaign campaign;

    @OneToOne
    @JoinColumn(name="incident_id")
    private Incident incident;

    @OneToOne
    @JoinColumn(name="lead_id")
    private Incident lead;

    public  Timeline() {}

    public Timeline (Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TimelineActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<TimelineActivity> activities) {
        this.activities = activities;
    }

    public void addActivity(TimelineActivity activity) {
        if (this.activities == null) {
            this.activities = Lists.newArrayList();
        }

        this.activities.add(activity);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public PersonalActivity getPersonalActivity() {
        return personalActivity;
    }

    public void setPersonalActivity(PersonalActivity personalActivity) {
        this.personalActivity = personalActivity;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public Incident getLead() {
        return lead;
    }

    public void setLead(Incident lead) {
        this.lead = lead;
    }
}
