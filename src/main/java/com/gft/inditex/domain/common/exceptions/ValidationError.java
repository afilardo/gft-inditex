package com.gft.inditex.domain.common.exceptions;

public abstract class ValidationError extends DomainError {
  protected ValidationError(String message) {
    super(message);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.INVALID;
  }

  public abstract String fieldName();
}
