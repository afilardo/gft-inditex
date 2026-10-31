package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;

public class TariffPriority extends ValueObject<Integer> {
  private TariffPriority(int priority) {
    super(priority);
  }

  public static TariffPriority create(int priority) {
    return new TariffPriority(priority);
  }
}