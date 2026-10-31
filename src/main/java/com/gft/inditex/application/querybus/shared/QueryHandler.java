package com.gft.inditex.application.querybus.shared;

import com.gft.inditex.domain.common.exceptions.DomainError;

public interface QueryHandler<T, U extends Query<T>> {
  T handle(U query) throws DomainError;
}
