package com.crm.infrastructure.events.messages;


import com.crm.infrastructure.entity.sale.SalesOrder;

public class NewSalesOrderMessage {

    private SalesOrder salesOrder;

    public NewSalesOrderMessage(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public static NewSalesOrderMessage create(SalesOrder salesOrder) {
        return new NewSalesOrderMessage(salesOrder);
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

}

