package com.crm.auditing.domain.model.negotiation;



import com.crm.auditing.domain.model.user.UserWhoChanged;
import com.crm.infrastructure.entity.builders.AbstractBuilder;

import java.util.Calendar;

public class NegotiationAuditBuilder extends AbstractBuilder<NegotiationAudit> {

	public NegotiationAuditBuilder() {
		this.entity = new NegotiationAudit();
	}

	public NegotiationAuditBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public NegotiationAuditBuilder withUserWhoChanged(UserWhoChanged user) {
		this.entity.setUser(user);
		return this;
	}
	
	public NegotiationAuditBuilder withInfo(String info) {
		this.entity.setJson(info);
		return this;
	}
	
	public NegotiationAuditBuilder setCurrentDate() {
		this.entity.setCreation(Calendar.getInstance().getTime());
		return this;
	}

	public NegotiationAuditBuilder withEntityId(Long entityId) {
		this.entity.setEntityId(entityId);
		return this;
	}

	public static NegotiationAuditBuilder createAuditing(Long id) {
		return new NegotiationAuditBuilder(id);
	}

	public static NegotiationAuditBuilder createAuditing() {
		return new NegotiationAuditBuilder();
	}


}
