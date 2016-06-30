package com.crm.register.view.clients;

import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.register.application.contract.ContactApplication;
import com.crm.register.infrastructure.validators.ContactValidator;
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
public class ClientContactController {

    @Autowired
    private ContactApplication service;

    @Autowired
    private ContactValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/clients/{clientId}/contacts/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientContact(@ModelAttribute @Validated Contact contact,
                                      @PathVariable Long clientId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(contact, createClient(clientId).build());
    }


    @RequestMapping(value = "/clients/{clientId}/contacts", method = RequestMethod.GET)
    public ModelAndView getContacts(@PathVariable Long clientId, Model model) {

        List<Contact> result = service.getContactsByClient(createClient(clientId).build());

        model.addAttribute("contactItems", result);
        model.addAttribute("client", createClient(clientId).build());
        return new ModelAndView("clients/edit-contact");
    }

}
