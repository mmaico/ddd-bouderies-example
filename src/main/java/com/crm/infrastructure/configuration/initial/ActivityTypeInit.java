package com.crm.infrastructure.configuration.initial;

import com.crm.infrastructure.configuration.parsers.ActivityTypeParser;
import com.crm.infrastructure.entity.assistants.calendar.ActivityType;
import com.crm.infrastructure.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class ActivityTypeInit implements InitialProcess {

	@Autowired
	private ActivityTypeRepository repository;


	@Override
	@PostConstruct
	public void run() {

		long count = repository.count();
		List<ActivityType> activities = ActivityTypeParser.getActivities();

		if (count < 1) {
            repository.save(activities);
        }
	}



	
}
