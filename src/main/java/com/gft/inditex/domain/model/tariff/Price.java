package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.ValueObject;
import com.gft.inditex.domain.common.money.Money;
import java.math.BigDecimal;

public class Price extends ValueObject<Money> {
  private Price(Money money) {
    super(money);
  }

  public static Price create(Money money) {
    return new Price(money);
  }

  public static Price create(BigDecimal amount, String currencyCode) {
    return new Price(Money.create(amount, currencyCode));
  }
}
