package com.crm.register.view.providers;

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

import static com.crm.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class ProviderContactController {

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

    @RequestMapping(value = "/providers/{providerId}/contacts/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientContact(@ModelAttribute @Validated Contact contact,
                                      @PathVariable Long providerId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(contact, createProvider(providerId).build());
    }


    @RequestMapping(value = "/providers/{providerId}/contacts", method = RequestMethod.GET)
    public ModelAndView getContacts(@PathVariable Long providerId, Model model) {

        List<Contact> result = service.getContactsByProvider(createProvider(providerId).build());

        model.addAttribute("contactItems", result);
        model.addAttribute("client", createProvider(providerId).build());
        return new ModelAndView("providers/edit-contact");
    }

}
