package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;
import com.gft.inditex.domain.common.datetime.DateTime;

public class TariffStartDate extends ValueObject<DateTime> {
  private TariffStartDate(DateTime dateTime) {
    super(dateTime);
  }

  public static TariffStartDate create(DateTime dateTime) {
    return new TariffStartDate(dateTime);
  }
}
