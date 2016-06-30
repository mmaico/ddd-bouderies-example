package com.crm.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QUser;
import com.crm.infrastructure.entity.enums.LogActivityTypeEnum;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLogActivity is a Querydsl query type for LogActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogActivity extends EntityPathBase<LogActivity> {

    private static final long serialVersionUID = -136245753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogActivity logActivity = new QLogActivity("logActivity");

    public final QTimelineActivity _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<AppFile, QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<LogActivityTypeEnum> type = createEnum("type", LogActivityTypeEnum.class);

    // inherited
    public final QUser user;

    public QLogActivity(String variable) {
        this(LogActivity.class, forVariable(variable), INITS);
    }

    public QLogActivity(Path<? extends LogActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLogActivity(PathMetadata<?> metadata, PathInits inits) {
        this(LogActivity.class, metadata, inits);
    }

    public QLogActivity(Class<? extends LogActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

