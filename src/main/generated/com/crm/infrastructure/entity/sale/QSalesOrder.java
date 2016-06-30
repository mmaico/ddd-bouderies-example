package com.crm.infrastructure.entity.sale;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QOperationRegion;
import com.crm.infrastructure.entity.QUser;
import com.crm.infrastructure.entity.person.QPerson;
import com.crm.infrastructure.entity.proposal.QBusinessProposal;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrder> {

    private static final long serialVersionUID = -1527365670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QPerson client;

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> deliveryForecast = createDateTime("deliveryForecast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOperationRegion operationRegion;

    public final ListPath<SalesOrderPaymentItem, QSalesOrderPaymentItem> paymentItems = this.<SalesOrderPaymentItem, QSalesOrderPaymentItem>createList("paymentItems", SalesOrderPaymentItem.class, QSalesOrderPaymentItem.class, PathInits.DIRECT2);

    public final QBusinessProposal proposal;

    public final ListPath<SalesOrderItem, QSalesOrderItem> salesOrderItems = this.<SalesOrderItem, QSalesOrderItem>createList("salesOrderItems", SalesOrderItem.class, QSalesOrderItem.class, PathInits.DIRECT2);

    public final QUser seller;

    public final BooleanPath taskGenerated = createBoolean("taskGenerated");

    public QSalesOrder(String variable) {
        this(SalesOrder.class, forVariable(variable), INITS);
    }

    public QSalesOrder(Path<? extends SalesOrder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrder.class, metadata, inits);
    }

    public QSalesOrder(Class<? extends SalesOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new QPerson(forProperty("client"), inits.get("client")) : null;
        this.operationRegion = inits.isInitialized("operationRegion") ? new QOperationRegion(forProperty("operationRegion")) : null;
        this.proposal = inits.isInitialized("proposal") ? new QBusinessProposal(forProperty("proposal"), inits.get("proposal")) : null;
        this.seller = inits.isInitialized("seller") ? new QUser(forProperty("seller"), inits.get("seller")) : null;
    }

}

