package com.crm.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QIdentifiable;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPersonProfile is a Querydsl query type for PersonProfile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonProfile extends EntityPathBase<PersonProfile> {

    private static final long serialVersionUID = -267146710L;

    public static final QPersonProfile personProfile = new QPersonProfile("personProfile");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPersonProfile(String variable) {
        super(PersonProfile.class, forVariable(variable));
    }

    public QPersonProfile(Path<? extends PersonProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonProfile(PathMetadata<?> metadata) {
        super(PersonProfile.class, metadata);
    }

}

