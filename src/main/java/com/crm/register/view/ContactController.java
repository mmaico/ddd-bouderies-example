package com.crm.register.view;

import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.timeline.Timeline;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.ContactApplication;
import com.crm.register.infrastructure.validators.ContactValidator;
import com.crm.timeline.application.TimelineFacade;
import com.crm.infrastructure.entity.builders.ContactBuilder;
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

import static com.crm.infrastructure.entity.builders.ContactBuilder.createContact;

@RestController
public class ContactController {

    @Autowired
    private ContactApplication service;

    @Autowired
    private ContactValidator validator;

    @Autowired
    private TimelineFacade timelineFacade;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/contacts/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Contact contact,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        Contact contactLoaded = service.register(contact);

        return "/contacts/" + contactLoaded.getId();
    }

    @RequestMapping(value = "/contacts/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute @Validated Contact contact,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(contact);
        Contact contactLoaded = service.register(contact);

        return "/contacts/" + contactLoaded.getId();
    }


    @RequestMapping(value="/contacts/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Contact> result = this.service.findAll(pager);

        model.addAttribute("contacts", result);
        return new ModelAndView("/contacts/list-items");
    }

    @RequestMapping(value="/contacts/create")
    public ModelAndView newClient(Model model) {

        return new ModelAndView("/contacts/edit");
    }

}
