package com.gft.inditex.domain.common.exceptions;

public abstract class DuplicatedError extends DomainError {
  protected DuplicatedError(String message) {
    super(message);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.DUPLICATED;
  }
}
