package com.crm.sales.domain.model.sale;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.sales.domain.model.customer.Customer;
import com.crm.sales.domain.model.negotiation.NegotiationClosedWon;
import com.crm.sales.domain.model.seller.Seller;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.crm.infrastructure.helpers.CollectionsHelper.isEmptySafe;


public class SalesOrder extends Identifiable {

    /**
	 *
	 */
	private static final long serialVersionUID = -3466031805155434986L;


    private Long id;

    private Customer customer;

    private Seller seller;

    private NegotiationClosedWon proposal;

    private List<SalesOrderItem> salesOrderItems;

    private List<SalesOrderPaymentItem> paymentItems;

    private Date creationDate;

    public SalesOrder(){}
    public SalesOrder(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addSalesOrderItem(SalesOrderItem item) {
        if (salesOrderItems == null) {
            this.salesOrderItems = Lists.newArrayList();
        }
        this.salesOrderItems.add(item);
    }

    public void addPayment(SalesOrderPaymentItem item) {
        if (paymentItems == null) {
            this.paymentItems = Lists.newArrayList();
        }
        this.paymentItems.add(item);
    }

    public BigDecimal getTotal() {

        if (isEmptySafe(this.getSalesOrderItems())) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = this.getSalesOrderItems()
                .stream()
                .map(e -> e.getPrice().multiply(new BigDecimal(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }

    public BigDecimal getTotalToPay() {

        if (isEmptySafe(this.getPaymentItems())) {
            return BigDecimal.ZERO;
        }

        BigDecimal totaToPay = this.getPaymentItems()
                .stream()
                .map(e -> e.getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totaToPay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public NegotiationClosedWon getProposal() {
        return proposal;
    }

    public void setProposal(NegotiationClosedWon proposal) {
        this.proposal = proposal;
    }

    public List<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(List<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public List<SalesOrderPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<SalesOrderPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
