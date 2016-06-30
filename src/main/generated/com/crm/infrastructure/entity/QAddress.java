package com.crm.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.location.QCity;
import com.crm.infrastructure.entity.location.QCountry;
import com.crm.infrastructure.entity.location.QState;
import com.crm.infrastructure.entity.person.QPerson;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 959506149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final QCity city;

    public final StringPath complement = createString("complement");

    public final QCountry country;

    public final StringPath district = createString("district");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final QPerson person;

    public final StringPath postalCode = createString("postalCode");

    public final QState state;

    public final StringPath street = createString("street");

    public final EnumPath<Address.Type> type = createEnum("type", Address.Type.class);

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata<?> metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city")) : null;
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.person = inits.isInitialized("person") ? new QPerson(forProperty("person"), inits.get("person")) : null;
        this.state = inits.isInitialized("state") ? new QState(forProperty("state")) : null;
    }

}

