package com.crm.sales.view;


import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.sales.application.SalesOrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class SalesOrderController {

    @Autowired
    private SalesOrderFacade service;


    @RequestMapping(value="/sales-order/{orderId}")
    public ModelAndView viewInfo(@PathVariable Long orderId, Model model) {

        Optional<SalesOrder> result = null; //this.service.getOne(orderId);

        model.addAttribute("salesOrder", result.isPresent() ? result.get() : null);
        return new ModelAndView("/sales/editSales");
    }

    @RequestMapping(value="/sales-order/list")
    public ModelAndView list(Model model) {

        List<SalesOrder> result = null; // this.service.findAllOrdered();

        model.addAttribute("salesOrders", result);
        return new ModelAndView("/sales/list");
    }
}
