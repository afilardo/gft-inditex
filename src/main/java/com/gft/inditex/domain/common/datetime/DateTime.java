package com.gft.inditex.domain.common.datetime;

import com.gft.inditex.domain.common.datetime.exceptions.DateTimeFormatIsNotIsoStandard;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DateTime {
  private final LocalDateTime localDateTime;

  public DateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public static DateTime now() {
    return new DateTime(LocalDateTime.now());
  }

  public static DateTime parse(String dateString) {
    ensureDateIsNotNull(dateString);

    try {
      return new DateTime(LocalDateTime.parse(dateString));
    } catch (DateTimeParseException ex) {
      throw new DateTimeFormatIsNotIsoStandard(dateString, ex);
    }
  }

  private static void ensureDateIsNotNull(String date) {
    if (date == null) {
      throw new DateTimeFormatIsNotIsoStandard(null);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DateTime other = (DateTime) o;
    return Objects.equals(this.localDateTime, other.localDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localDateTime);
  }

  @Override
  public String toString() {
    return localDateTime.toString();
  }
}
