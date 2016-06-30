package com.crm.infrastructure.entity.proposal;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.saleable.QSalePackage;
import com.crm.infrastructure.entity.saleable.QSaleableUnit;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProposalSaleableItem is a Querydsl query type for ProposalSaleableItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProposalSaleableItem extends EntityPathBase<ProposalSaleableItem> {

    private static final long serialVersionUID = -46078253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposalSaleableItem proposalSaleableItem = new QProposalSaleableItem("proposalSaleableItem");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QBusinessProposal businessProposal;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> originalPrice = createNumber("originalPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QSaleableUnit saleableUnit;

    public final QSalePackage salePackage;

    public QProposalSaleableItem(String variable) {
        this(ProposalSaleableItem.class, forVariable(variable), INITS);
    }

    public QProposalSaleableItem(Path<? extends ProposalSaleableItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProposalSaleableItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProposalSaleableItem(PathMetadata<?> metadata, PathInits inits) {
        this(ProposalSaleableItem.class, metadata, inits);
    }

    public QProposalSaleableItem(Class<? extends ProposalSaleableItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessProposal = inits.isInitialized("businessProposal") ? new QBusinessProposal(forProperty("businessProposal"), inits.get("businessProposal")) : null;
        this.saleableUnit = inits.isInitialized("saleableUnit") ? new QSaleableUnit(forProperty("saleableUnit")) : null;
        this.salePackage = inits.isInitialized("salePackage") ? new QSalePackage(forProperty("salePackage")) : null;
    }

}

