package com.crm.infrastructure.entity.leads;

import com.crm.auditing.infrastructure.ExcludeAuditingField;
import com.crm.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="lead_phones")
public class LeadPhone extends Identifiable {

    public enum PhoneType {
        BUSINESS, HOME_PHONE, CELL
    }
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @ExcludeAuditingField
    private Lead lead;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }
}
