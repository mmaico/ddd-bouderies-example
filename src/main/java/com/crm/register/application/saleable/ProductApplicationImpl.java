package com.crm.register.application.saleable;

import com.crm.infrastructure.entity.saleable.Product;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.Saleable.ProductRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.saleable.ProductApplication;
import com.crm.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductApplicationImpl extends BaseModelServiceImpl<Product> implements ProductApplication {

    private SaleableUnitDomainService domainService;

    private ProductRepository productRepository;

    @Autowired
    public ProductApplicationImpl(ProductRepository productRepository, SaleableUnitDomainService domainService) {
        this.productRepository = productRepository;
        this.domainService = domainService;
    }

    public Product register(Product product) {

        return super.save(product, domainService);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return productRepository.getOne(id);
    }

    public BaseRepository<Product, Long> getRepository() {
        return productRepository;
    }

}
