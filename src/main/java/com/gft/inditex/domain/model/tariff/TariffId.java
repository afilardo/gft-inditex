package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;

public class TariffId extends ValueObject<Integer> {
  private TariffId(int id) {
    super(id);
  }

  public static TariffId create(int id) {
    return new TariffId(id);
  }
}
