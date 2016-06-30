package com.crm.negotiation.domain.model.negotiation;

import com.crm.negotiation.domain.model.seller.Seller;


public class SellerChangeNegotiation {

  private final Seller seller;

  private final Negotiation negotiation;


  public SellerChangeNegotiation(Seller seller, Negotiation negotiation) {
    this.seller = seller;
    this.negotiation = negotiation;
  }

  public Seller getSeller() {
    return seller;
  }

  public Negotiation getNegotiation() {
    return negotiation;
  }

  public static SellerChangeNegotiation create(Seller seller, Negotiation negotiation) {
    return new SellerChangeNegotiation(seller, negotiation);
  }

}
