package com.gft.inditex.domain.model.tariff.exceptions;

import com.gft.inditex.domain.common.exceptions.NotFoundError;

public class TariffNotFound extends NotFoundError {
  public TariffNotFound() {
    super("tariff not found for the given criteria");
  }
}
