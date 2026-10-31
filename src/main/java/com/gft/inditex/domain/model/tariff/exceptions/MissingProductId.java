package com.gft.inditex.domain.model.tariff.exceptions;

import com.gft.inditex.domain.common.exceptions.ValidationError;

public class MissingProductId extends ValidationError {
  public MissingProductId() {
    super("'productId' field can not be empty");
  }

  @Override
  public String fieldName() {
    return "productId";
  }
}
