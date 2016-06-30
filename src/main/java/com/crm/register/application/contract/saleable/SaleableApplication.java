package com.crm.register.application.contract.saleable;

import com.crm.infrastructure.entity.saleable.SaleableType;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.service.ModelService;

import java.util.List;

public interface SaleableApplication extends ModelService<SaleableUnit> {


    List<SaleableUnit> getByType(SaleableType saleableType);
}
