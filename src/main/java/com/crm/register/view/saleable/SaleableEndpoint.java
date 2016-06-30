package com.crm.register.view.saleable;

import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class SaleableEndpoint {

    @Autowired
    private SaleableApplication service;



    @RequestMapping(value = "/rs/saleable/{saleableId}", method = RequestMethod.GET)
    public @ResponseBody
    SaleableUnit getSaleable(@PathVariable Long saleableId) {

        Optional<SaleableUnit> saleable = service.getOne(saleableId);

        return saleable.isPresent() ? saleable.get() : null;
    }



}
