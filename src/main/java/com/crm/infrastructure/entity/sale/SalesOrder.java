package com.crm.infrastructure.entity.sale;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.crm.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Entity
@Table(name = "sales_order")
public class SalesOrder extends Identifiable {

    /**
	 *
	 */
	private static final long serialVersionUID = -3466031805155434986L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="person_id")
    @NotNull(message = "order.person.required")
    private Person client;

    @ManyToOne
    @JoinColumn(name="seller_id")
    @NotNull(message = "order.seller.required")
    private User seller;

    @OneToOne
    @JoinColumn(name="proposal_id")
    @NotNull(message = "order.domain.required")
    private BusinessProposal proposal;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @Column(name="delivery_forecast")
    private Date deliveryForecast;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrder")
    @Valid
    private List<SalesOrderItem> salesOrderItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrder")
    @Valid
    private List<SalesOrderPaymentItem> paymentItems;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "order.region.required")
    private OperationRegion operationRegion;

    @Column(name="task_generated")
    private Boolean taskGenerated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_date")
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

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }

    public List<SalesOrderPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<SalesOrderPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }

    public OperationRegion getOperationRegion() {
        return operationRegion;
    }

    public void setOperationRegion(OperationRegion operationRegion) {
        this.operationRegion = operationRegion;
    }

    public List<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(List<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }

    public Boolean getTaskGenerated() {
        return taskGenerated;
    }

    public void setTaskGenerated(Boolean taskGenerated) {
        this.taskGenerated = taskGenerated;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
