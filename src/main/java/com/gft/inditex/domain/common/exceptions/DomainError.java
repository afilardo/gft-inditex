package com.gft.inditex.domain.common.exceptions;

public abstract class DomainError extends RuntimeException {
  protected DomainError(String message) {
    super(message);
  }

  protected DomainError(String message, Throwable cause) {
    super(message, cause);
  }

  public String errorCode() {
    return domainErrorCode().toString();
  }

  public abstract DomainErrorCode domainErrorCode();
}
