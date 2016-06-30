package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.auditing.BusinessProposalAudinting;

import java.util.Calendar;

public class BusinessProposalAuditingBuilder extends AbstractBuilder<BusinessProposalAudinting>  {

	public BusinessProposalAuditingBuilder() {
		this.entity = new BusinessProposalAudinting();
	}

	public BusinessProposalAuditingBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public BusinessProposalAuditingBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}
	
	public BusinessProposalAuditingBuilder withInfo(String info) {
		this.entity.setInfo(info);
		return this;
	}
	
	public BusinessProposalAuditingBuilder setCurrentDate() {
		this.entity.setLastUpdate(Calendar.getInstance().getTime());
		return this;
	}

	public BusinessProposalAuditingBuilder withEntityId(Long entityId) {
		this.entity.setEntityId(entityId);
		return this;
	}

	public static BusinessProposalAuditingBuilder createAuditing(Long id) {
		return new BusinessProposalAuditingBuilder(id);
	}

	public static BusinessProposalAuditingBuilder createAuditing() {
		return new BusinessProposalAuditingBuilder();
	}


}
