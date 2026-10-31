package com.gft.inditex.domain.model.tariff.exceptions;

import com.gft.inditex.domain.common.exceptions.ValidationError;

public class InvalidDate extends ValidationError {
  public InvalidDate() {
    super("'date' field has invalid format");
  }

  @Override
  public String fieldName() {
    return "date";
  }
}
