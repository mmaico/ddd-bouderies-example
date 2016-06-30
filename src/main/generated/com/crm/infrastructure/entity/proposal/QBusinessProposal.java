package com.crm.infrastructure.entity.proposal;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QOperationRegion;
import com.crm.infrastructure.entity.QUser;
import com.crm.infrastructure.entity.enums.ProposalTemperature;
import com.crm.infrastructure.entity.person.QPerson;
import com.crm.infrastructure.entity.timeline.QTimeline;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBusinessProposal is a Querydsl query type for BusinessProposal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBusinessProposal extends EntityPathBase<BusinessProposal> {

    private static final long serialVersionUID = -1363083457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposal businessProposal = new QBusinessProposal("businessProposal");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath careOf = createString("careOf");

    public final QPerson client;

    public final DateTimePath<java.util.Date> deliveryForeCast = createDateTime("deliveryForeCast", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final QOperationRegion operationRegion;

    public final ListPath<ProposalPaymentItem, QProposalPaymentItem> paymentItems = this.<ProposalPaymentItem, QProposalPaymentItem>createList("paymentItems", ProposalPaymentItem.class, QProposalPaymentItem.class, PathInits.DIRECT2);

    public final ListPath<ProposalSaleableItem, QProposalSaleableItem> saleableItems = this.<ProposalSaleableItem, QProposalSaleableItem>createList("saleableItems", ProposalSaleableItem.class, QProposalSaleableItem.class, PathInits.DIRECT2);

    public final QUser seller;

    public final EnumPath<ProposalTemperature> temperature = createEnum("temperature", ProposalTemperature.class);

    public final QTimeline timeline;

    public QBusinessProposal(String variable) {
        this(BusinessProposal.class, forVariable(variable), INITS);
    }

    public QBusinessProposal(Path<? extends BusinessProposal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposal(PathMetadata<?> metadata, PathInits inits) {
        this(BusinessProposal.class, metadata, inits);
    }

    public QBusinessProposal(Class<? extends BusinessProposal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new QPerson(forProperty("client"), inits.get("client")) : null;
        this.operationRegion = inits.isInitialized("operationRegion") ? new QOperationRegion(forProperty("operationRegion")) : null;
        this.seller = inits.isInitialized("seller") ? new QUser(forProperty("seller"), inits.get("seller")) : null;
        this.timeline = inits.isInitialized("timeline") ? new QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

