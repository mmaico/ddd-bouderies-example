package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.sale.SalesOrderItem;
import com.crm.infrastructure.entity.sale.SalesOrderPaymentItem;

import java.util.Date;
import java.util.List;

public class SalesOrderBuilder extends AbstractBuilder<SalesOrder>  {

	public SalesOrderBuilder() {
		this.entity = new SalesOrder();
	}

	public SalesOrderBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalesOrderBuilder createSalesOrder(Long id) {
		return new SalesOrderBuilder(id);
	}

	public static SalesOrderBuilder createSalesOrder() {
		return new SalesOrderBuilder();
	}

    public SalesOrderBuilder withClient(Person client) {
        this.entity.setClient(client);
        return this;
    }

    public SalesOrderBuilder withSeller(User user) {
        this.entity.setSeller(user);
        return this;
    }

    public SalesOrderBuilder withCurrentDate() {
        this.entity.setCreationDate(new Date());
        return this;
    }

    public SalesOrderBuilder withDeliveryForeCast(Date deliveryForeCast) {
        this.entity.setDeliveryForecast(deliveryForeCast);
        return this;
    }

    public SalesOrderBuilder withOperationRegion(OperationRegion operationRegion) {
        this.entity.setOperationRegion(operationRegion);
        return this;
    }

    public SalesOrderBuilder withSalesOrderItems(List<SalesOrderItem> items) {
        this.entity.setSalesOrderItems(items);
        return this;
    }

    public SalesOrderBuilder withProposal(BusinessProposal proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

    public SalesOrderBuilder addSalesOrderItem(SalesOrderItem item) {
        item.setSalesOrder(this.entity);
        this.entity.addSalesOrderItem(item);
        return this;
    }

    public SalesOrderBuilder addPayment(SalesOrderPaymentItem item) {
        item.setSalesOrder(this.entity);
        this.entity.addPayment(item);
        return this;
    }



}
