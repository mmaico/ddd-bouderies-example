package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.proposal.ProposalSaleableItem;
import com.crm.infrastructure.entity.saleable.SalePackage;
import com.crm.infrastructure.entity.saleable.SaleableUnit;

import java.math.BigDecimal;

public class ProposalSaleableItemBuilder extends AbstractBuilder<ProposalSaleableItem>  {

	public ProposalSaleableItemBuilder() {
		this.entity = new ProposalSaleableItem();
	}

	public ProposalSaleableItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public ProposalSaleableItemBuilder withPrice(BigDecimal price) {
		this.entity.setPrice(price);
		return this;
	}

    public ProposalSaleableItemBuilder withOriginalPrice(BigDecimal originalPrice) {
        this.entity.setOriginalPrice(originalPrice);
        return this;
    }

    public ProposalSaleableItemBuilder withQuantity(Integer quantity) {
        this.entity.setQuantity(quantity);
        return this;
    }

    public ProposalSaleableItemBuilder withProposal(BusinessProposal proposal) {
        this.entity.setBusinessProposal(proposal);
        return this;
    }

    public ProposalSaleableItemBuilder withSaleable(SaleableUnit saleable) {
        this.entity.setSaleableUnit(saleable);
        return this;
    }

    public ProposalSaleableItemBuilder withPackage(SalePackage salePackageSaleable) {
        this.entity.setSalePackage(salePackageSaleable);
        return this;
    }


	public static ProposalSaleableItemBuilder createProposalSaleable(Long id) {
		return new ProposalSaleableItemBuilder(id);
	}

	public static ProposalSaleableItemBuilder create() {
		return new ProposalSaleableItemBuilder();
	}
}
