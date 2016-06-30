package com.crm.register.view.clients;

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

@RestController
public class ClientAddressController {

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

    @RequestMapping(value = "/clients/{clientId}/addresses/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientAddress(@ModelAttribute @Validated Address address,
                                             @PathVariable Long clientId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(address);
        normalizeEntityRequest.addFieldsToUpdate(address);
        service.register(address, createClient(clientId).build());
    }


    @RequestMapping(value = "/clients/{clientId}/addresses", method = RequestMethod.GET)
    public ModelAndView getAddress(@PathVariable Long clientId, Model model) {

        List<Address> result = service.getAddressesByClient(createClient(clientId).build());

        model.addAttribute("addressItems", result);
        model.addAttribute("client", createClient(clientId).build());
        return new ModelAndView("clients/edit-address");
    }

}
