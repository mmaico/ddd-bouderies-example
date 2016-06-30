package com.crm.negotiation.domain.model.seller;


import java.util.Optional;

public interface SellerRepository {

  boolean exists(Long id);

  Optional<Seller> findOne(Long sallerId);
}
