package com.crm.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.QAppFile;
import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QOperationRegion;
import com.crm.infrastructure.entity.saleable.QSaleableUnit;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskTemplate is a Querydsl query type for TaskTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskTemplate extends EntityPathBase<TaskTemplate> {

    private static final long serialVersionUID = 1882787033L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskTemplate taskTemplate = new QTaskTemplate("taskTemplate");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final ListPath<ChecklistTemplate, QChecklistTemplate> checklistTemplates = this.<ChecklistTemplate, QChecklistTemplate>createList("checklistTemplates", ChecklistTemplate.class, QChecklistTemplate.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final ListPath<AppFile, QAppFile> files = this.<AppFile, QAppFile>createList("files", AppFile.class, QAppFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Integer> quantityDaysTofinishAfertSignedContract = createNumber("quantityDaysTofinishAfertSignedContract", Integer.class);

    public final QOperationRegion region;

    public final QSaleableUnit saleable;

    public final ListPath<TaskCostTemplate, QTaskCostTemplate> tasksCostsTemplates = this.<TaskCostTemplate, QTaskCostTemplate>createList("tasksCostsTemplates", TaskCostTemplate.class, QTaskCostTemplate.class, PathInits.DIRECT2);

    public final ListPath<TaskTemplate, QTaskTemplate> templatesChilds = this.<TaskTemplate, QTaskTemplate>createList("templatesChilds", TaskTemplate.class, QTaskTemplate.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QTaskTemplate(String variable) {
        this(TaskTemplate.class, forVariable(variable), INITS);
    }

    public QTaskTemplate(Path<? extends TaskTemplate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskTemplate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskTemplate(PathMetadata<?> metadata, PathInits inits) {
        this(TaskTemplate.class, metadata, inits);
    }

    public QTaskTemplate(Class<? extends TaskTemplate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new QOperationRegion(forProperty("region")) : null;
        this.saleable = inits.isInitialized("saleable") ? new QSaleableUnit(forProperty("saleable")) : null;
    }

}

