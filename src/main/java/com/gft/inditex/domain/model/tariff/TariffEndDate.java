package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;
import com.gft.inditex.domain.common.datetime.DateTime;

public class TariffEndDate extends ValueObject<DateTime> {
  private TariffEndDate(DateTime dateTime) {
    super(dateTime);
  }

  public static TariffEndDate create(DateTime dateTime) {
    return new TariffEndDate(dateTime);
  }
}
