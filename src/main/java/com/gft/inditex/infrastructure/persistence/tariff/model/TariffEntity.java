package com.gft.inditex.infrastructure.persistence.tariff.model;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES",
    check = @CheckConstraint(
        name = "check_prices_dates",
        constraint = "END_DATE > START_DATE"
    ))
public class TariffEntity {
  @Id
  @Column(name = "PRICE_LIST")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "BRAND_ID", nullable = false)
  @Min(1)
  private int brandId;

  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "PRODUCT_ID", nullable = false)
  @Min(1)
  private int productId;

  @Column(name = "PRIORITY", nullable = false)
  @Min(0)
  private int priority;

  @Column(name = "PRICE", nullable = false)
  private BigDecimal amount;

  @Column(name = "CURR", nullable = false, length = 3)
  private String currency;

  @Version
  private long version;

  public int getId() {
    return id;
  }

  public int getBrandId() {
    return brandId;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public int getProductId() {
    return productId;
  }

  public int getPriority() {
    return priority;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public long getVersion() {
    return version;
  }
}
