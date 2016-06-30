package com.crm.register.application.saleable;

import com.crm.infrastructure.entity.saleable.SaleableType;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.Saleable.SaleableUnitRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("saleableApplication")
public class SaleableApplicationImpl extends BaseModelServiceImpl<SaleableUnit> implements SaleableApplication {



    private SaleableUnitRepository saleableRepository;

    @Autowired
    public SaleableApplicationImpl(SaleableUnitRepository saleableRepository) {
        this.saleableRepository = saleableRepository;
    }


    public BaseRepository<SaleableUnit, Long> getRepository() {
        return saleableRepository;
    }

    @Override
    public List<SaleableUnit> getByType(SaleableType saleableType) {
        if (saleableType == null) {
            Lists.newArrayList();
        }
        return saleableRepository.getByType(saleableType);
    }
}
