package com.crm.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QUser;
import com.crm.infrastructure.entity.enums.ApproverStatus;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBusinessProposalApprovalActivity is a Querydsl query type for BusinessProposalApprovalActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBusinessProposalApprovalActivity extends EntityPathBase<BusinessProposalApprovalActivity> {

    private static final long serialVersionUID = 1365943888L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessProposalApprovalActivity businessProposalApprovalActivity = new QBusinessProposalApprovalActivity("businessProposalApprovalActivity");

    public final QTimelineActivity _super;

    public final EnumPath<ApproverStatus> avaluation = createEnum("avaluation", ApproverStatus.class);

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<AppFile, QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final QUser user;

    public QBusinessProposalApprovalActivity(String variable) {
        this(BusinessProposalApprovalActivity.class, forVariable(variable), INITS);
    }

    public QBusinessProposalApprovalActivity(Path<? extends BusinessProposalApprovalActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposalApprovalActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBusinessProposalApprovalActivity(PathMetadata<?> metadata, PathInits inits) {
        this(BusinessProposalApprovalActivity.class, metadata, inits);
    }

    public QBusinessProposalApprovalActivity(Class<? extends BusinessProposalApprovalActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

