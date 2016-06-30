package com.crm.infrastructure.entity.sale;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSalesOrderPaymentItem is a Querydsl query type for SalesOrderPaymentItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrderPaymentItem extends EntityPathBase<SalesOrderPaymentItem> {

    private static final long serialVersionUID = 299338239L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrderPaymentItem salesOrderPaymentItem = new QSalesOrderPaymentItem("salesOrderPaymentItem");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observation = createString("observation");

    public final QSalesOrder salesOrder;

    public final NumberPath<java.math.BigDecimal> value = createNumber("value", java.math.BigDecimal.class);

    public QSalesOrderPaymentItem(String variable) {
        this(SalesOrderPaymentItem.class, forVariable(variable), INITS);
    }

    public QSalesOrderPaymentItem(Path<? extends SalesOrderPaymentItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrderPaymentItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrderPaymentItem(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrderPaymentItem.class, metadata, inits);
    }

    public QSalesOrderPaymentItem(Class<? extends SalesOrderPaymentItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesOrder = inits.isInitialized("salesOrder") ? new QSalesOrder(forProperty("salesOrder"), inits.get("salesOrder")) : null;
    }

}

