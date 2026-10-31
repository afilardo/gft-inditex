package com.gft.inditex.domain.model.tariff;

import java.util.Objects;

public class Tariff {
  private final TariffId id;
  private final BrandId brandId;
  private final TariffStartDate startDate;
  private final TariffEndDate endDate;
  private final ProductId productId;
  private final TariffPriority priority;
  private final Price price;

  private Tariff(TariffId id, BrandId brandId, TariffStartDate startDate, TariffEndDate endDate, ProductId productId,
                 TariffPriority priority, Price price) {
    this.id = id;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.productId = productId;
    this.priority = priority;
    this.price = price;
  }

  public static Tariff create(TariffId id, BrandId brandId, TariffStartDate startDate, TariffEndDate endDate,
                              ProductId productId, TariffPriority priority, Price price) {
    return new Tariff(id, brandId, startDate, endDate, productId, priority, price);
  }

  public TariffId getId() {
    return id;
  }

  public BrandId getBrandId() {
    return brandId;
  }

  public TariffStartDate getStartDate() {
    return startDate;
  }

  public TariffEndDate getEndDate() {
    return endDate;
  }

  public ProductId getProductId() {
    return productId;
  }

  public TariffPriority getPriority() {
    return priority;
  }

  public Price getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tariff that = (Tariff) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Tariff{"
        + "id=" + id
        + ", brandId=" + brandId
        + ", startDate=" + startDate
        + ", endDate=" + endDate
        + ", productId=" + productId
        + ", priority=" + priority
        + ", price=" + price
        + '}';
  }
}
