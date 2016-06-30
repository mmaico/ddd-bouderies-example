package com.crm.register.application.saleable;

import com.crm.infrastructure.entity.saleable.SalePackage;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.Saleable.SalesPackageRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.saleable.SalePackageApplication;
import com.crm.register.domain.contract.SaleableUnitDomainService;
import com.crm.register.domain.contract.SalesPackageAddSaleableDomainService;
import com.crm.infrastructure.helpers.HandlerErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.Sets.newHashSet;

@Service("salePackageApplication")
public class SalesPackageApplicationImpl extends BaseModelServiceImpl<SalePackage> implements SalePackageApplication {

    @Autowired
    private SaleableUnitDomainService domainService;

    @Autowired
    private SalesPackageRepository salesPackageRepository;

    @Autowired
    private SalesPackageAddSaleableDomainService addSaleableDomainService;


    @Override
    public SalePackage register(SalePackage salePackageItem) {

        return super.save(salePackageItem, domainService);
    }

    @Override
    public Page<SalePackage> findAll(Pageable pageable) {
        return this.salesPackageRepository.findAll(pageable);
    }

    @Override
    public SalePackage addProductOrService(SalePackage salePackage, SaleableUnit saleable) {

        Optional<SalePackage> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            HandlerErrors.hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }
        addSaleableDomainService.checkBusinessRulesFor(saleable);
        salePackageLoaded.get().addSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    @Override
    public SalePackage removeProductOrService(SalePackage salePackage, SaleableUnit saleable) {

        Optional<SalePackage> salePackageLoaded = salesPackageRepository.getOne(salePackage.getId());

        if (!salePackageLoaded.isPresent()) {
            HandlerErrors.hasErrors(newHashSet("salepackage.not.found")).throwing(ValidationException.class);
        }

        salePackageLoaded.get().removeSaleableUnit(saleable);

        return salePackageLoaded.get();
    }

    public Optional<SalePackage> getOne(Long id) {
        return salesPackageRepository.getOne(id);
    }

    public BaseRepository<SalePackage, Long> getRepository() {
        return salesPackageRepository;
    }

}
