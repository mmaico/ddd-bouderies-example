package com.crm.infrastructure.entity.person;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.QAddress;
import com.crm.infrastructure.entity.QContact;
import com.crm.infrastructure.entity.timeline.QTimeline;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIndividual is a Querydsl query type for Individual
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIndividual extends EntityPathBase<Individual> {

    private static final long serialVersionUID = 537779395L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIndividual individual = new QIndividual("individual");

    public final QPerson _super;

    //inherited
    public final BooleanPath active;

    //inherited
    public final ListPath<Address, QAddress> addresses;

    //inherited
    public final ListPath<Contact, QContact> contacts;

    public final StringPath cpf = createString("cpf");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath lastname = createString("lastname");

    //inherited
    public final StringPath name;

    // inherited
    public final QPersonProfile profile;

    // inherited
    public final QTimeline timeline;

    public QIndividual(String variable) {
        this(Individual.class, forVariable(variable), INITS);
    }

    public QIndividual(Path<? extends Individual> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIndividual(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIndividual(PathMetadata<?> metadata, PathInits inits) {
        this(Individual.class, metadata, inits);
    }

    public QIndividual(Class<? extends Individual> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QPerson(type, metadata, inits);
        this.active = _super.active;
        this.addresses = _super.addresses;
        this.contacts = _super.contacts;
        this.id = _super.id;
        this.name = _super.name;
        this.profile = _super.profile;
        this.timeline = _super.timeline;
    }

}

