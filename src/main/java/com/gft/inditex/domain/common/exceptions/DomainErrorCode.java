package com.gft.inditex.domain.common.exceptions;

public enum DomainErrorCode {
  INVALID("INVALID"),
  NOT_FOUND("NOT FOUND"),
  DUPLICATED("DUPLICATED"),
  INTERNAL("INTERNAL");

  private final String description;

  DomainErrorCode(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
