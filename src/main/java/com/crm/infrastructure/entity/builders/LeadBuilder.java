package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.leads.Lead;

public class LeadBuilder extends AbstractBuilder<Lead>  {

	public LeadBuilder() {
		this.entity = new Lead();
	}

	public LeadBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static LeadBuilder createLead(Long id) {
		return new LeadBuilder(id);
	}

	public static LeadBuilder createLead() {
		return new LeadBuilder();
	}
}
