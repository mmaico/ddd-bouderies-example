package com.crm.infrastructure.entity.assistants.calendar;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActivityType is a Querydsl query type for ActivityType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityType extends EntityPathBase<ActivityType> {

    private static final long serialVersionUID = -1638869521L;

    public static final QActivityType activityType = new QActivityType("activityType");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QActivityType(String variable) {
        super(ActivityType.class, forVariable(variable));
    }

    public QActivityType(Path<? extends ActivityType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityType(PathMetadata<?> metadata) {
        super(ActivityType.class, metadata);
    }

}

