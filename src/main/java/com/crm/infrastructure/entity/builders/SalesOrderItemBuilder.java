package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.entity.sale.SalesOrderItem;
import com.crm.infrastructure.entity.saleable.SalePackage;
import com.crm.infrastructure.entity.saleable.SaleableUnit;

import java.math.BigDecimal;

public class SalesOrderItemBuilder extends AbstractBuilder<SalesOrderItem>  {

	public SalesOrderItemBuilder() {
		this.entity = new SalesOrderItem();
	}

	public SalesOrderItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalesOrderItemBuilder createSalesOrderItem(Long id) {
		return new SalesOrderItemBuilder(id);
	}

	public static SalesOrderItemBuilder createSalesOrderItem() {
		return new SalesOrderItemBuilder();
	}

    public SalesOrderItemBuilder withSaleable(SaleableUnit saleableUnit) {
        this.entity.setSaleableUnit(saleableUnit);
        return this;
    }

    public SalesOrderItemBuilder withSalesPackage(SalePackage salesPackage) {
        this.entity.setSalePackage(salesPackage);
        return this;
    }

    public SalesOrderItemBuilder withPrice(BigDecimal price) {
        this.entity.setPrice(price);
        return this;
    }

    public SalesOrderItemBuilder withOriginalPrice(BigDecimal originalPrice) {
        this.entity.setOriginalPrice(originalPrice);
        return this;
    }

    public SalesOrderItemBuilder withQuantity(Integer quantity) {
        this.entity.setQuantity(quantity);
        return this;
    }

    public SalesOrderItemBuilder withSalesOrder(SalesOrder salesOrder) {
        this.entity.setSalesOrder(salesOrder);
        return this;
    }

}
