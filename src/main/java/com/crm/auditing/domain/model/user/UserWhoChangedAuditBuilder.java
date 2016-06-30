package com.crm.auditing.domain.model.user;



import com.crm.infrastructure.entity.builders.AbstractBuilder;

public class UserWhoChangedAuditBuilder extends AbstractBuilder<UserWhoChanged>  {

	public UserWhoChangedAuditBuilder() {
		this.entity = new UserWhoChanged();
	}

	public UserWhoChangedAuditBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public static UserWhoChangedAuditBuilder create(Long id) {
		return new UserWhoChangedAuditBuilder(id);
	}


}
