package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.WorkspaceUnit;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;

public class ActDeliverySalesBuilder extends AbstractBuilder<WorkspaceUnit>  {

	public ActDeliverySalesBuilder() {
		this.entity = new WorkspaceUnit();
	}

	public ActDeliverySalesBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ActDeliverySalesBuilder withSalesOrder(SalesOrder salesOrder) {
		this.entity.setSalesOrder(salesOrder);
		return this;
	}

	public ActDeliverySalesBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}

	public static ActDeliverySalesBuilder createActDelivery(Long id) {
		return new ActDeliverySalesBuilder(id);
	}

	public static ActDeliverySalesBuilder createActDelivery() {
		return new ActDeliverySalesBuilder();
	}
}
