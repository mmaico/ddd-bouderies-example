package com.crm.infrastructure.entity.proposal.requestapproval;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QUser;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApproverProfile is a Querydsl query type for ApproverProfile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApproverProfile extends EntityPathBase<ApproverProfile> {

    private static final long serialVersionUID = 1077468923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApproverProfile approverProfile = new QApproverProfile("approverProfile");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QUser approver;

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QApproverProfile(String variable) {
        this(ApproverProfile.class, forVariable(variable), INITS);
    }

    public QApproverProfile(Path<? extends ApproverProfile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApproverProfile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApproverProfile(PathMetadata<?> metadata, PathInits inits) {
        this(ApproverProfile.class, metadata, inits);
    }

    public QApproverProfile(Class<? extends ApproverProfile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approver = inits.isInitialized("approver") ? new QUser(forProperty("approver"), inits.get("approver")) : null;
    }

}

