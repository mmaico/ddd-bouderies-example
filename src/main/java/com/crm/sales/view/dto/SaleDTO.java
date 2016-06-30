package com.crm.sales.view.dto;

import com.crm.infrastructure.entity.builders.ProposalSaleableItemBuilder;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.proposal.ProposalSaleableItem;
import com.google.common.collect.Lists;

import java.util.List;


public class SaleDTO {

    private BusinessProposal proposal = new BusinessProposal();

    private List<SaleableItemDTO> items = Lists.newArrayList();

    public List<SaleableItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SaleableItemDTO> items) {
        this.items = items;
    }

    public BusinessProposal get() {

        List<ProposalSaleableItem> saleableItems = Lists.newArrayList();

        for (SaleableItemDTO dto: items) {
            ProposalSaleableItem item = ProposalSaleableItemBuilder.create()
                    .withPackage(dto.getIpackage())
                    .withPrice(dto.getPrice())
                    .withQuantity(dto.getQuantity())
                    .withSaleable(dto.getSaleableUnit()).build();

            saleableItems.add(item);
        }

        proposal.setSaleableItems(saleableItems);

        return proposal;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }
}
