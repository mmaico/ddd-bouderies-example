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
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = -199540077L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompany company = new QCompany("company");

    public final QPerson _super;

    //inherited
    public final BooleanPath active;

    //inherited
    public final ListPath<Address, QAddress> addresses;

    public final StringPath ccm = createString("ccm");

    public final StringPath cnpj = createString("cnpj");

    //inherited
    public final ListPath<Contact, QContact> contacts;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath ie = createString("ie");

    //inherited
    public final StringPath name;

    // inherited
    public final QPersonProfile profile;

    // inherited
    public final QTimeline timeline;

    public final StringPath tradingName = createString("tradingName");

    public QCompany(String variable) {
        this(Company.class, forVariable(variable), INITS);
    }

    public QCompany(Path<? extends Company> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompany(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompany(PathMetadata<?> metadata, PathInits inits) {
        this(Company.class, metadata, inits);
    }

    public QCompany(Class<? extends Company> type, PathMetadata<?> metadata, PathInits inits) {
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

