package com.crm.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.assistants.calendar.QCalendar;
import com.crm.infrastructure.entity.proposal.requestapproval.QApproverProfile;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -948572550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QApproverProfile approverProfile;

    public final ArrayPath<byte[], Byte> avatar = createArray("avatar", byte[].class);

    public final QBranch branch;

    public final QCalendar calendar;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath login = createString("login");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final QUserPosition position;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approverProfile = inits.isInitialized("approverProfile") ? new QApproverProfile(forProperty("approverProfile"), inits.get("approverProfile")) : null;
        this.branch = inits.isInitialized("branch") ? new QBranch(forProperty("branch")) : null;
        this.calendar = inits.isInitialized("calendar") ? new QCalendar(forProperty("calendar"), inits.get("calendar")) : null;
        this.position = inits.isInitialized("position") ? new QUserPosition(forProperty("position")) : null;
    }

}

