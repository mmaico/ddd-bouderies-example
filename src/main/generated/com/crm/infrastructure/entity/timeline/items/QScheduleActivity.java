package com.crm.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QUser;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QScheduleActivity is a Querydsl query type for ScheduleActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QScheduleActivity extends EntityPathBase<ScheduleActivity> {

    private static final long serialVersionUID = -43161678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleActivity scheduleActivity = new QScheduleActivity("scheduleActivity");

    public final QTimelineActivity _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final ListPath<AppFile, QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath title = createString("title");

    // inherited
    public final QUser user;

    public QScheduleActivity(String variable) {
        this(ScheduleActivity.class, forVariable(variable), INITS);
    }

    public QScheduleActivity(Path<? extends ScheduleActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleActivity(PathMetadata<?> metadata, PathInits inits) {
        this(ScheduleActivity.class, metadata, inits);
    }

    public QScheduleActivity(Class<? extends ScheduleActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

