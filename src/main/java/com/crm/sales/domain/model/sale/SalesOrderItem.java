package com.crm.sales.domain.model.sale;


import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.saleable.SalePackage;
import com.crm.infrastructure.entity.saleable.SaleableUnit;

import java.math.BigDecimal;

public class SalesOrderItem extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3881704814612452364L;

    private Long id;

    private SaleableUnit saleableUnit;

    private SalePackage salePackage;

    private BigDecimal price;

    private BigDecimal originalPrice;

    private Integer quantity = 0;

    private SalesOrder salesOrder;


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnit getSaleableUnit() {
        return saleableUnit;
    }

    public void setSaleableUnit(SaleableUnit saleableUnit) {
        this.saleableUnit = saleableUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SalePackage getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(SalePackage salePackage) {
        this.salePackage = salePackage;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public SaleableUnit getSaleableAvailable() {
        return this.getSaleableUnit() != null ? this.getSaleableUnit() : this.getSalePackage();
    }
}
