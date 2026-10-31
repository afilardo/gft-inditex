package com.gft.inditex.domain.common.money.exceptions;

import com.gft.inditex.domain.common.exceptions.DomainError;
import com.gft.inditex.domain.common.exceptions.DomainErrorCode;

public class CurrencyCodeIsNotIsoStandard extends DomainError {
  public CurrencyCodeIsNotIsoStandard(String currencyCode) {
    super(constructMessage(currencyCode));
  }

  public CurrencyCodeIsNotIsoStandard(String currencyCode, Throwable cause) {
    super(constructMessage(currencyCode), cause);
  }

  private static String constructMessage(String currencyCode) {
    return String.format("Currency code [%s] must be an  ISO 4217 code", currencyCode);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.INVALID;
  }

}
