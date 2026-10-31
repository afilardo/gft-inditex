package com.gft.inditex.domain.model.tariff.exceptions;

import com.gft.inditex.domain.common.exceptions.ValidationError;

public class MissingBrandId extends ValidationError {
  public MissingBrandId() {
    super("'brandId' field can not be empty");
  }

  @Override
  public String fieldName() {
    return "brandId";
  }
}
