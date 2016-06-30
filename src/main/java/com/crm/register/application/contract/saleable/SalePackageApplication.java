package com.crm.register.application.contract.saleable;

import com.crm.infrastructure.entity.saleable.SalePackage;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.service.ModelService;

public interface SalePackageApplication extends ModelService<SalePackage> {

    SalePackage register(SalePackage salePackageItem);

    SalePackage addProductOrService(SalePackage salePackage, SaleableUnit saleable);

    SalePackage removeProductOrService(SalePackage salePackage, SaleableUnit saleable);


}
