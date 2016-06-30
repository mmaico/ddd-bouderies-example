package com.crm.infrastructure.entity.assistants.archive;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QUser;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfo> {

    private static final long serialVersionUID = 856068128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final StringPath description = createString("description");

    public final QAppFile file;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final QUser owner;

    public final ListPath<SharedWith, QSharedWith> sharedWith = this.<SharedWith, QSharedWith>createList("sharedWith", SharedWith.class, QSharedWith.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QFileInfo(String variable) {
        this(FileInfo.class, forVariable(variable), INITS);
    }

    public QFileInfo(Path<? extends FileInfo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileInfo(PathMetadata<?> metadata, PathInits inits) {
        this(FileInfo.class, metadata, inits);
    }

    public QFileInfo(Class<? extends FileInfo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QAppFile(forProperty("file")) : null;
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner"), inits.get("owner")) : null;
    }

}

