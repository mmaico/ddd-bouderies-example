package com.crm.delivery.view;

import com.crm.delivery.application.WorkspaceApplication;
import com.crm.infrastructure.entity.WorkspaceUnit;
import com.crm.infrastructure.entity.builders.ActDeliverySalesBuilder;
import com.crm.infrastructure.entity.builders.SalesOrderBuilder;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.crm.infrastructure.entity.builders.SalesOrderBuilder.createSalesOrder;

@RestController
public class DeliveryDashboardController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private WorkspaceApplication application;

    @RequestMapping(value="/delivery/dashboard")
    public ModelAndView showDashboard() {

        return new ModelAndView("/delivery/delivery-dashboard");
    }

    @RequestMapping(value="/delivery/act/proposal/{salesId}", method = RequestMethod.POST)
    public void act(@PathVariable("salesId") Long salesId) {

        WorkspaceUnit workspaceUnit = ActDeliverySalesBuilder.createActDelivery()
                .withUser(security.getPrincipal().getUser())
                .withSalesOrder(createSalesOrder(salesId).build()).build();

        application.register(workspaceUnit);
    }

    @RequestMapping(value="/delivery/salesorder/{salesorderId}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Long salesorderId) {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(salesorderId).build();

        application.removeItemWorkspaceBy(salesOrder, security.getPrincipal().getUser());
    }
}
