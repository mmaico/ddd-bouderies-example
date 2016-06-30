package com.crm.infrastructure.entity.assistants.calendar;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPeriod is a Querydsl query type for Period
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPeriod extends EntityPathBase<Period> {

    private static final long serialVersionUID = 1307816263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPeriod period = new QPeriod("period");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QCalendarActivity calendarActivity;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllDay = createBoolean("isAllDay");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QPeriod(String variable) {
        this(Period.class, forVariable(variable), INITS);
    }

    public QPeriod(Path<? extends Period> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPeriod(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPeriod(PathMetadata<?> metadata, PathInits inits) {
        this(Period.class, metadata, inits);
    }

    public QPeriod(Class<? extends Period> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendarActivity = inits.isInitialized("calendarActivity") ? new QCalendarActivity(forProperty("calendarActivity"), inits.get("calendarActivity")) : null;
    }

}

