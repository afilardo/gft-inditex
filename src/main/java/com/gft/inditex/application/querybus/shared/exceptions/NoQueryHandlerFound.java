package com.gft.inditex.application.querybus.shared.exceptions;

import com.gft.inditex.domain.common.exceptions.DomainError;
import com.gft.inditex.domain.common.exceptions.DomainErrorCode;

public class NoQueryHandlerFound extends DomainError {
  public NoQueryHandlerFound(String message) {
    super(message);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.INVALID;
  }

}
