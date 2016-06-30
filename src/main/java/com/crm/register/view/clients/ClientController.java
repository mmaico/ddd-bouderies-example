package com.crm.register.view.clients;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.LocationHelper;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.ClientApplication;
import com.crm.register.infrastructure.validators.ClientVOValidator;
import com.crm.register.view.dto.ClientDTO;
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
public class ClientController {

    @Autowired
    private ClientApplication service;

    @Autowired
    private ClientVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private LocationHelper locationHelper;

    @InitBinder(value = "clientDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }



    @RequestMapping(value = "/clients/save", method = RequestMethod.POST)
    public @ResponseBody String save(@ModelAttribute @Validated ClientDTO clientDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(clientDTO);
        Person person = clientDTO.getClient();

        normalizeEntityRequest.addFieldsToUpdate(person);
        normalizeEntityRequest.doNestedReference(person);
        Client clientSaved = service.register(person);

        return "/clients/" + clientSaved.getId();
    }

    @RequestMapping(value = "/clients/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute ClientDTO clientDTO, BindingResult bindingResult, Model model) {



        normalizeEntityRequest.addFieldsToUpdate(clientDTO);
        Person person = clientDTO.getClient();

        normalizeEntityRequest.addFieldsToUpdate(person);
        Client clientSaved = service.register(person);

        return "/clients/" + clientSaved.getId();
    }

    @RequestMapping(value="/clients/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Person> result = this.service.findAll(pager);

        model.addAttribute("clients", result);
        return new ModelAndView("/clients/list-items");
    }

    @RequestMapping(value="/clients/create")
    public ModelAndView newClient(Model model) {

        model.addAttribute("countries", locationHelper.getAllCountries());
        return new ModelAndView("/clients/newClient");
    }
    
    @RequestMapping(value="/clients/{clientId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long clientId, Model model) {
        
        Optional<Person> result = this.service.getOne(clientId);

        model.addAttribute("countries", locationHelper.getAllCountries());
        model.addAttribute("client", result.isPresent() ? result.get(): null);
        return new ModelAndView("/clients/" + templateName);
    }
}
