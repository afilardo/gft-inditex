package com.gft.inditex.domain.common;

import java.util.Objects;

public abstract class ValueObject<T> {
  private final T value;

  public T get() {
    return value;
  }

  protected ValueObject(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValueObject<?> that = (ValueObject<?>) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
