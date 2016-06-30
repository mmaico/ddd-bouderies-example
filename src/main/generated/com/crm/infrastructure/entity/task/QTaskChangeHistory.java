package com.crm.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskChangeHistory is a Querydsl query type for TaskChangeHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskChangeHistory extends EntityPathBase<TaskChangeHistory> {

    private static final long serialVersionUID = -991230523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskChangeHistory taskChangeHistory = new QTaskChangeHistory("taskChangeHistory");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final DateTimePath<java.util.Date> dateOfChange = createDateTime("dateOfChange", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<TaskStatus> statusChanged = createEnum("statusChanged", TaskStatus.class);

    public final QTask taskChanged;

    public QTaskChangeHistory(String variable) {
        this(TaskChangeHistory.class, forVariable(variable), INITS);
    }

    public QTaskChangeHistory(Path<? extends TaskChangeHistory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskChangeHistory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskChangeHistory(PathMetadata<?> metadata, PathInits inits) {
        this(TaskChangeHistory.class, metadata, inits);
    }

    public QTaskChangeHistory(Class<? extends TaskChangeHistory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskChanged = inits.isInitialized("taskChanged") ? new QTask(forProperty("taskChanged"), inits.get("taskChanged")) : null;
    }

}

