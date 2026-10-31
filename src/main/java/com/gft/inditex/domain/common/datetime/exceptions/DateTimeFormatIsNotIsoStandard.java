package com.gft.inditex.domain.common.datetime.exceptions;


import com.gft.inditex.domain.common.exceptions.DomainError;
import com.gft.inditex.domain.common.exceptions.DomainErrorCode;

public class DateTimeFormatIsNotIsoStandard extends DomainError {
  public DateTimeFormatIsNotIsoStandard(String date) {
    super(constructMessage(date));
  }

  public DateTimeFormatIsNotIsoStandard(String date, Throwable cause) {
    super(constructMessage(date), cause);
  }

  private static String constructMessage(String date) {
    return String.format("DateTime [%s] must be an ISO-8601 compliant UTC timestamp", date);
  }

  @Override
  public DomainErrorCode domainErrorCode() {
    return DomainErrorCode.INVALID;
  }
}
