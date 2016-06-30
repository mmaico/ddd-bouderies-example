package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.saleable.SalePackage;

public class SalePackageBuilder extends AbstractBuilder<SalePackage>  {

	public SalePackageBuilder() {
		this.entity = new SalePackage();
	}

	public SalePackageBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalePackageBuilder createPackage(Long id) {
		return new SalePackageBuilder(id);
	}

	public static SalePackageBuilder createPackage() {
		return new SalePackageBuilder();
	}
}
