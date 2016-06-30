package com.crm.infrastructure.configuration.initial;

import com.crm.infrastructure.configuration.parsers.RegionsParser;
import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class OperationRegionInit implements InitialProcess {


	@Autowired
	private RegionRepository repository;


	@Override
	@PostConstruct
	public void run() {

		long count = repository.count();
		if (count < 1) {
            List<OperationRegion> result = RegionsParser.getRegions();

            repository.save(result);
        }
	}

	
}
