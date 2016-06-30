package com.crm.infrastructure.entity.timeline.items;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("task")
public class TaskActivity extends TimelineActivity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6505997363895736083L;
	


}
