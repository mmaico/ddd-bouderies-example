package com.crm.infrastructure.entity.enums;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.proposal.BusinessProposal;
import com.crm.infrastructure.entity.sale.SalesOrder;

public enum EntityName {

    BUSINESS_PROPOSAL(BusinessProposal.class), PERSON(Person.class), SALES_ORDER(SalesOrder.class);

    private Class clazz;

    EntityName(Class clazz) {
        this.clazz = clazz;
    }

    public static EntityName get(Class clazz) {

        for (EntityName value:values()) {
            if (value.getClazz() == clazz || value.getClazz().isAssignableFrom(clazz)) {
                return value;
            }
        }

        return null;
    }

    public Class getClazz() {
        return this.clazz;
    }
}
