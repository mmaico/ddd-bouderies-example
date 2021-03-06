package com.crm.negotiation.domain.model.negotiation;


import com.crm.infrastructure.entity.Identifiable;
import com.crm.negotiation.domain.model.product.Product;

import java.math.BigDecimal;

public class NegotiationItem extends Identifiable {

  private Long id;

  private Product product;

  private Integer quantity;

  private BigDecimal price;

  @Override public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
