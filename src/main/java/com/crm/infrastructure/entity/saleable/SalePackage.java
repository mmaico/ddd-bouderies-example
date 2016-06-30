package com.crm.infrastructure.entity.saleable;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages")
public class SalePackage extends SaleableUnit {

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="package_saleable", joinColumns=@JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="saleable_id"))
    private List<SaleableUnit> saleableUnits;

    @Column(name="price_by_products")
    private Boolean priceByProducts = Boolean.FALSE;

    public SalePackage(Long id) {
        super(id);
        setType(SaleableType.PACKAGE);
    }

    public SalePackage() {
        super();
        setType(SaleableType.PACKAGE);
    }

    public List<SaleableUnit> getSaleableUnits() {
        return saleableUnits;
    }

    public void setSaleableUnits(List<SaleableUnit> saleableUnits) {
        this.saleableUnits = saleableUnits;
    }

    public Boolean getPriceByProducts() {
        return priceByProducts;
    }

    public Boolean calcPriceByProducts() {
        return priceByProducts == null ? Boolean.FALSE : priceByProducts;
    }

    public void setPriceByProducts(Boolean priceByProducts) {
        this.priceByProducts = priceByProducts;
    }

    public void addSaleableUnit(SaleableUnit saleableUnit) {
        if (this.saleableUnits == null) {
            this.saleableUnits = Lists.newArrayList();
        }
        this.saleableUnits.add(saleableUnit);
    }

    public void removeSaleableUnit(SaleableUnit saleableUnit) {
        if (this.saleableUnits == null) {
            return;
        }

        this.saleableUnits.remove(saleableUnit);
    }
}
