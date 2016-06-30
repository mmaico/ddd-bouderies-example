package com.crm.infrastructure.entity;

import com.crm.infrastructure.entity.sale.SalesOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="workspace_units")
public class WorkspaceUnit extends Identifiable {

	private static final long serialVersionUID = 2011235325379126884L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sales_order_id")
    @NotNull(message = "act.delivery.sales.order.is.null")
	private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @NotNull(message = "act.delivery.user.is.null")
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
