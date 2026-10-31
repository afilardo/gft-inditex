package com.gft.inditex.domain.common.money;

import com.gft.inditex.domain.common.money.exceptions.CurrencyCodeIsNotIsoStandard;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Money {
  private final BigDecimal amount;
  private final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Money create(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  public static Money create(BigDecimal amount, String currencyCode) {
    try {
      return new Money(amount, Currency.getInstance(currencyCode));
    } catch (IllegalArgumentException  ex) {
      throw new CurrencyCodeIsNotIsoStandard(currencyCode);
    }
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    return amount + " " + currency;
  }
}
