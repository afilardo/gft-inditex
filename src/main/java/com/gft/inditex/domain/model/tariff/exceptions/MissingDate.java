package com.gft.inditex.domain.model.tariff.exceptions;

import com.gft.inditex.domain.common.exceptions.ValidationError;

public class MissingDate extends ValidationError {
  public MissingDate() {
    super("'date' field can not be empty");
  }

  @Override
  public String fieldName() {
    return "date";
  }
}
