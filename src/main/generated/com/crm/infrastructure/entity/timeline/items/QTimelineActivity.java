package com.crm.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QUser;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTimelineActivity is a Querydsl query type for TimelineActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTimelineActivity extends EntityPathBase<TimelineActivity> {

    private static final long serialVersionUID = -1897898148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimelineActivity timelineActivity = new QTimelineActivity("timelineActivity");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final StringPath description = createString("description");

    public final ListPath<AppFile, QAppFile> files = this.<AppFile, QAppFile>createList("files", AppFile.class, QAppFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QTimelineActivity(String variable) {
        this(TimelineActivity.class, forVariable(variable), INITS);
    }

    public QTimelineActivity(Path<? extends TimelineActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineActivity(PathMetadata<?> metadata, PathInits inits) {
        this(TimelineActivity.class, metadata, inits);
    }

    public QTimelineActivity(Class<? extends TimelineActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

