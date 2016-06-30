package com.crm.infrastructure.entity.timeline;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.crm.infrastructure.entity.QContact;
import com.crm.infrastructure.entity.QIdentifiable;
import com.crm.infrastructure.entity.QIncident;
import com.crm.infrastructure.entity.activities.QPersonalActivity;
import com.crm.infrastructure.entity.campaigns.QCampaign;
import com.crm.infrastructure.entity.person.QPerson;
import com.crm.infrastructure.entity.proposal.QBusinessProposal;
import com.crm.infrastructure.entity.task.QTask;
import com.crm.infrastructure.entity.timeline.items.QTimelineActivity;
import com.crm.infrastructure.entity.timeline.items.TimelineActivity;
import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTimeline is a Querydsl query type for Timeline
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTimeline extends EntityPathBase<Timeline> {

    private static final long serialVersionUID = 772235583L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeline timeline = new QTimeline("timeline");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final ListPath<TimelineActivity, QTimelineActivity> activities = this.<TimelineActivity, QTimelineActivity>createList("activities", TimelineActivity.class, QTimelineActivity.class, PathInits.DIRECT2);

    public final QCampaign campaign;

    public final QContact contact;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIncident incident;

    public final QIncident lead;

    public final QPerson person;

    public final QPersonalActivity personalActivity;

    public final QBusinessProposal proposal;

    public final QTask task;

    public QTimeline(String variable) {
        this(Timeline.class, forVariable(variable), INITS);
    }

    public QTimeline(Path<? extends Timeline> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimeline(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimeline(PathMetadata<?> metadata, PathInits inits) {
        this(Timeline.class, metadata, inits);
    }

    public QTimeline(Class<? extends Timeline> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new QCampaign(forProperty("campaign"), inits.get("campaign")) : null;
        this.contact = inits.isInitialized("contact") ? new QContact(forProperty("contact"), inits.get("contact")) : null;
        this.incident = inits.isInitialized("incident") ? new QIncident(forProperty("incident"), inits.get("incident")) : null;
        this.lead = inits.isInitialized("lead") ? new QIncident(forProperty("lead"), inits.get("lead")) : null;
        this.person = inits.isInitialized("person") ? new QPerson(forProperty("person"), inits.get("person")) : null;
        this.personalActivity = inits.isInitialized("personalActivity") ? new QPersonalActivity(forProperty("personalActivity"), inits.get("personalActivity")) : null;
        this.proposal = inits.isInitialized("proposal") ? new QBusinessProposal(forProperty("proposal"), inits.get("proposal")) : null;
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task"), inits.get("task")) : null;
    }

}

