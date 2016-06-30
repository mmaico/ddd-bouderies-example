package com.crm.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QOperationRegion;
import com.crm.infrastructure.entity.QUser;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.enums.TaskStatus;
import com.crm.infrastructure.entity.sale.QSalesOrder;
import com.crm.infrastructure.entity.timeline.QTimeline;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 218598207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTask task = new QTask("task");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final ListPath<Checklist, QChecklist> checklist = this.<Checklist, QChecklist>createList("checklist", Checklist.class, QChecklist.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final QOperationRegion region;

    public final QSalesOrder salesOrder;

    public final ListPath<User, QUser> signedBy = this.<User, QUser>createList("signedBy", User.class, QUser.class, PathInits.DIRECT2);

    public final EnumPath<TaskStatus> status = createEnum("status", TaskStatus.class);

    public final ListPath<TaskCost, QTaskCost> taskCosts = this.<TaskCost, QTaskCost>createList("taskCosts", TaskCost.class, QTaskCost.class, PathInits.DIRECT2);

    public final ListPath<Task, QTask> tasksChilds = this.<Task, QTask>createList("tasksChilds", Task.class, QTask.class, PathInits.DIRECT2);

    public final QTimeline timeline;

    public final StringPath title = createString("title");

    public final ListPath<ScheduleTriggerNotification, QScheduleTriggerNotification> triggerNotifications = this.<ScheduleTriggerNotification, QScheduleTriggerNotification>createList("triggerNotifications", ScheduleTriggerNotification.class, QScheduleTriggerNotification.class, PathInits.DIRECT2);

    public QTask(String variable) {
        this(Task.class, forVariable(variable), INITS);
    }

    public QTask(Path<? extends Task> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTask(PathMetadata<?> metadata, PathInits inits) {
        this(Task.class, metadata, inits);
    }

    public QTask(Class<? extends Task> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new QOperationRegion(forProperty("region")) : null;
        this.salesOrder = inits.isInitialized("salesOrder") ? new QSalesOrder(forProperty("salesOrder"), inits.get("salesOrder")) : null;
        this.timeline = inits.isInitialized("timeline") ? new QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

