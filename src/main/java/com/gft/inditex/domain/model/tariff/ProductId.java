package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;

public class ProductId extends ValueObject<Integer> {
  private ProductId(int productId) {
    super(productId);
  }

  public static ProductId create(int productId) {
    return new ProductId(productId);
  }
}
