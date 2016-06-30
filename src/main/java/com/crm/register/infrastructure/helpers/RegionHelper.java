package com.crm.register.infrastructure.helpers;

import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionHelper {

    @Autowired
    private RegionRepository repository;

    public Iterable<OperationRegion> getRegions() {
        return  repository.findAll();
    }
}
