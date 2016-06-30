package com.crm.register.view.providers;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.LocationHelper;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.ProviderApplication;
import com.crm.register.infrastructure.validators.ProviderVOValidator;
import com.crm.register.view.dto.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class ProviderController {

    @Autowired
    private ProviderApplication service;

    @Autowired
    private ProviderVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private LocationHelper locationHelper;

    @InitBinder(value = "providerDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.POST)
    public @ResponseBody String save(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(providerDTO);
        Person provider = providerDTO.getProvider();

        normalizeEntityRequest.addFieldsToUpdate(provider);
        normalizeEntityRequest.doNestedReference(provider);

        Provider providerSaved = service.register(provider);

        model.addAttribute("provider", providerSaved);
        return "/providers/" + providerSaved.getId();
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(providerDTO.getProvider());
        Provider clientSaved = service.register(providerDTO.getProvider());

        model.addAttribute("provider", clientSaved);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/providers/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Person> result = this.service.findAll(pager);

        model.addAttribute("providers", result);
        return new ModelAndView("/providers/list-items");
    }

    @RequestMapping(value="/providers/create")
    public ModelAndView newProvider(Model model) {

        model.addAttribute("countries", locationHelper.getAllCountries());
        return new ModelAndView("/providers/newProvider");
    }
    
    @RequestMapping(value="/providers/{providerId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long providerId, Model model) {
        
        Optional<Person> result = this.service.getOne(providerId);

        model.addAttribute("countries", locationHelper.getAllCountries());
        model.addAttribute("provider", result.isPresent() ? result.get(): null);
        return new ModelAndView("/providers/" + templateName);
    }
}
