package com.crm.delivery.view;

import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.security.helpers.SecurityHelper;
import com.crm.sales.application.SalesOrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class DeliverySalesTasksController {

    @Autowired
    private SalesOrderFacade application;

    @Autowired
    private SecurityHelper security;

    @RequestMapping(value="/delivery/sales-order/{salesId}")
    public ModelAndView showDetail(@PathVariable Long salesId, Model model) {
        Optional<SalesOrder> result = Optional.empty(); // application.getOne(salesId);

        model.addAttribute("user", security.getPrincipal().getUser());
        model.addAttribute("salesorder", result);
        return new ModelAndView("/delivery/sales-order-tasks");
    }

}
