package com.gft.inditex.domain.common.exceptions;

public abstract class NotFoundError extends DomainError {
  protected NotFoundError(String message) {
    super(message);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.NOT_FOUND;
  }
}
