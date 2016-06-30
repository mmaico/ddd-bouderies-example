package com.crm.sales.infrastructure.generatebyproposal.convert;


import com.crm.infrastructure.entity.builders.SalesOrderBuilder;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.sale.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalToSaleOrder implements Converter<BusinessProposal, SalesOrder> {

    @Autowired
    private ProposalPaymentToSalesOrderPayment paymentConverter;

    @Autowired
    private ProposalSaleableItemToSalesOrderItem orderItemConverter;

    @Override
    public SalesOrder convert(BusinessProposal source) {

        SalesOrderBuilder builder = SalesOrderBuilder.createSalesOrder()
                .withClient(source.getClient())
                .withProposal(source)
                .withDeliveryForeCast(source.getDeliveryForeCast())
                .withOperationRegion(source.getOperationRegion())
                .withCurrentDate()
                .withSeller(source.getSeller());


        source.getPaymentItems().stream()
                .forEach(payment -> builder.addPayment(paymentConverter.convert(payment)));

        source.getSaleableItems().stream()
                .forEach(salesItem -> builder.addSalesOrderItem(orderItemConverter.convert(salesItem)));

        return builder.build();
    }
}
