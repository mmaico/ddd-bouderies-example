package com.crm.infrastructure.entity.proposal.requestapproval;

import com.crm.auditing.infrastructure.ExcludeAuditingField;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.User;

import javax.persistence.*;


@Entity
@Table(name="proposal_aprover_profile")
public class ApproverProfile extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name="user_approver_id")
    @ExcludeAuditingField
    private User approver;

    private Boolean available = Boolean.FALSE;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
