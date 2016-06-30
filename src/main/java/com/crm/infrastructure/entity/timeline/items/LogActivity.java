package com.crm.infrastructure.entity.timeline.items;


import com.crm.infrastructure.entity.enums.LogActivityTypeEnum;
import com.crm.infrastructure.helpers.files.annotations.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@Media(name="timelines")
@DiscriminatorValue("logactivity")
public class LogActivity extends TimelineActivity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2728388686834419769L;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LogActivityTypeEnum type;

    public LogActivity(){}

    public LogActivity(Long id) {
        this.setId(id);
    }

    public LogActivityTypeEnum getType() {
        return type;
    }

    public void setType(LogActivityTypeEnum type) {
        this.type = type;
    }
}
