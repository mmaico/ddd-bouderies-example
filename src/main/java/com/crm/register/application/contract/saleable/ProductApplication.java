package com.crm.register.application.contract.saleable;

import com.crm.infrastructure.entity.saleable.Product;
import com.crm.infrastructure.service.ModelService;

public interface ProductApplication extends ModelService<Product> {


    Product register(Product product);
}
