package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;

public class BrandId extends ValueObject<Integer> {
  private BrandId(int brandId) {
    super(brandId);
  }

  public static BrandId create(int brandId) {
    return new BrandId(brandId);
  }
}
