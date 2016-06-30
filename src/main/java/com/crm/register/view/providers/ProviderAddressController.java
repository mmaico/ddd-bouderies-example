package com.crm.register.view.providers;

import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.register.application.contract.AddressApplication;
import com.crm.register.infrastructure.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.crm.infrastructure.entity.builders.ClientBuilder.createClient;
import static com.crm.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class ProviderAddressController {

    @Autowired
    private AddressApplication service;

    @Autowired
    private AddressValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "address")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/providers/{providerId}/addresses/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientAddress(@ModelAttribute @Validated Address address,
                                             @PathVariable Long providerId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(address);
        normalizeEntityRequest.addFieldsToUpdate(address);
        service.register(address, createProvider(providerId).build());
    }


    @RequestMapping(value = "/providers/{providerId}/addresses", method = RequestMethod.GET)
    public ModelAndView getAddress(@PathVariable Long providerId, Model model) {

        List<Address> result = service.getAddressesByProvider(createProvider(providerId).build());

        model.addAttribute("addressItems", result);
        model.addAttribute("provider", createProvider(providerId).build());
        return new ModelAndView("providers/edit-address");
    }

}
