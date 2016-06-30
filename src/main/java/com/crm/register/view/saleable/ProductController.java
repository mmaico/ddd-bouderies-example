package com.crm.register.view.saleable;

import com.crm.infrastructure.entity.saleable.Product;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.saleable.ProductApplication;
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
public class ProductController {

    @Autowired
    private ProductApplication service;

    @Autowired
    private SaleableValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"product"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
    public @ResponseBody
    SaleableUnit save(@ModelAttribute @Validated Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SaleableUnit saleable = service.register(product);

        return saleable;
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.PUT)
    public @ResponseBody
    void update(@ModelAttribute Product product) {

        normalizeEntityRequest.addFieldsToUpdate(product);
        service.register(product);
    }

    @RequestMapping("/products/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Product> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/products/{productId}")
    public ModelAndView viewInfo(@PathVariable Long productId, Model model) {
        
        Optional<Product> result = this.service.getOne(productId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/products/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
