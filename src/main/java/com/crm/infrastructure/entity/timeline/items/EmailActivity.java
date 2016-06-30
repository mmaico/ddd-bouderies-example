package com.crm.infrastructure.entity.timeline.items;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("email")
public class EmailActivity extends TimelineActivity {


    /**
	 * 
	 */
	private static final long serialVersionUID = -2555190729836666717L;

    @Column(name="email_to")
	private String to;

    @Column(name="email_from")
    private String from;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
