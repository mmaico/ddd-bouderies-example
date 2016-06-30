package com.crm.sales.infrastructure.generatebyproposal.convert;

import com.crm.infrastructure.entity.builders.SalesOrderPaymentItemBuilder;
import com.crm.infrastructure.entity.proposal.ProposalPaymentItem;
import com.crm.infrastructure.entity.sale.SalesOrderPaymentItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalPaymentToSalesOrderPayment implements Converter<ProposalPaymentItem, SalesOrderPaymentItem> {


    @Override
    public SalesOrderPaymentItem convert(ProposalPaymentItem source) {

        return SalesOrderPaymentItemBuilder.createSalesOrderPaymentItem()
                    .withDueDate(source.getDueDate())
                    .withObservation(source.getObservation())
                    .withValue(source.getValue()).build();

    }
}
