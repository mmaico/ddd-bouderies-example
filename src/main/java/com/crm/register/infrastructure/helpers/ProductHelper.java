package com.crm.register.infrastructure.helpers;

import com.crm.infrastructure.entity.saleable.SaleableType;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductHelper {

    private static final Map<SaleableType, String> names = new HashMap<>();

    @Autowired
    private SaleableApplication service;

    static {
        names.put(SaleableType.PRODUCT, "Produto");
        names.put(SaleableType.SERVICE, "Servi&ccedil;o");
        names.put(SaleableType.PACKAGE, "Pacote");
    }

    public String type(SaleableType type) {
        return names.get(type);
    }

    public Iterable<SaleableUnit> getAllProducts() {
        return service.findAll(Pager.build().withPageNumer(1).withPageSize(10000));
    }

    public Iterable<SaleableUnit> getProducts() {
        return service.getByType(SaleableType.PRODUCT);
    }

    public Iterable<SaleableUnit> getServices() {
        return service.getByType(SaleableType.SERVICE);
    }

    public Iterable<SaleableUnit> getPackages() {
        return service.getByType(SaleableType.PACKAGE);
    }

    public SaleableUnit load(Long id) {
        Optional<SaleableUnit> result = service.getOne(id);

        return result.isPresent() ? result.get() : null;
    }

    public Boolean isPackage(SaleableUnit saleableUnit) {
        if (saleableUnit == null || saleableUnit.getType() == null) {
            return Boolean.FALSE;
        }

        return SaleableType.PACKAGE.equals(saleableUnit.getType());
    }
}
