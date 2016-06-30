package com.crm.infrastructure.repository.Saleable;

import com.crm.infrastructure.entity.saleable.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepository extends BaseSaleableRepository<Product> {

    @Query("SELECT p FROM Product AS p WHERE p.type = 'PRODUCT' AND p.id = :id")
    Optional<Product> getOne(@Param("id")Long id);

    @Query("SELECT p FROM Product AS p WHERE p.type = 'PRODUCT' ORDER BY p.name")
    Page<Product> findAll(Pageable pageable);
}
