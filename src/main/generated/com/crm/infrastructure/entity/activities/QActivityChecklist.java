package com.crm.infrastructure.entity.activities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActivityChecklist is a Querydsl query type for ActivityChecklist
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityChecklist extends EntityPathBase<ActivityChecklist> {

    private static final long serialVersionUID = -2137363195L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityChecklist activityChecklist = new QActivityChecklist("activityChecklist");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QPersonalActivity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDone = createBoolean("isDone");

    public final StringPath name = createString("name");

    public QActivityChecklist(String variable) {
        this(ActivityChecklist.class, forVariable(variable), INITS);
    }

    public QActivityChecklist(Path<? extends ActivityChecklist> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityChecklist(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityChecklist(PathMetadata<?> metadata, PathInits inits) {
        this(ActivityChecklist.class, metadata, inits);
    }

    public QActivityChecklist(Class<? extends ActivityChecklist> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QPersonalActivity(forProperty("activity"), inits.get("activity")) : null;
    }

}

