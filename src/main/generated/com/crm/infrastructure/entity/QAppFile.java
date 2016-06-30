package com.crm.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAppFile is a Querydsl query type for AppFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAppFile extends EntityPathBase<AppFile> {

    private static final long serialVersionUID = 1312831022L;

    public static final QAppFile appFile = new QAppFile("appFile");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lasMotification = createDateTime("lasMotification", java.util.Date.class);

    public final StringPath mimeType = createString("mimeType");

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QAppFile(String variable) {
        super(AppFile.class, forVariable(variable));
    }

    public QAppFile(Path<? extends AppFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppFile(PathMetadata<?> metadata) {
        super(AppFile.class, metadata);
    }

}

