package com.gft.inditex.application.querybus.shared;

import com.gft.inditex.domain.common.exceptions.DomainError;

public interface QueryBus {
  <T> T handle(Query<T> query) throws DomainError;
}
