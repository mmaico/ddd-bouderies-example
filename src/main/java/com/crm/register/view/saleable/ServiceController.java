package com.crm.register.view.saleable;

import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.entity.saleable.Service;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.Pager;

import com.crm.register.application.contract.saleable.ServiceSaleableApplication;
import com.crm.register.infrastructure.validators.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class ServiceController {

    @Autowired
    private ServiceSaleableApplication service;

    @Autowired
    private SaleableValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"service"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/services/save", method = RequestMethod.POST)
    public @ResponseBody
    SaleableUnit save(@ModelAttribute @Validated Service service, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SaleableUnit saleable = this.service.register(service);

        return saleable;
    }

    @RequestMapping(value = "/services/save", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Service service, BindingResult bindingResult) {
        normalizeEntityRequest.addFieldsToUpdate(service);
        this.service.register(service);
    }

    @RequestMapping("/services/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Service> result = this.service.findAll(pager);

        model.addAttribute("services", result);
        return new ModelAndView("/saleables/services/serviceList");
    }
    
    @RequestMapping(value="/services/{serviceId}")
    public ModelAndView viewInfo(@PathVariable Long serviceId, Model model) {
        
        Optional<Service> result = this.service.getOne(serviceId);

        model.addAttribute("service", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/services/serviceDetail");
    }

    @RequestMapping(value="/services/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/services/serviceEdit");
    }

}
