package com.crm.infrastructure.entity.assistants.calendar;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QUser;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = 2068737348L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final ListPath<CalendarActivity, QCalendarActivity> activities = this.<CalendarActivity, QCalendarActivity>createList("activities", CalendarActivity.class, QCalendarActivity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QCalendar(String variable) {
        this(Calendar.class, forVariable(variable), INITS);
    }

    public QCalendar(Path<? extends Calendar> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCalendar(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCalendar(PathMetadata<?> metadata, PathInits inits) {
        this(Calendar.class, metadata, inits);
    }

    public QCalendar(Class<? extends Calendar> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

