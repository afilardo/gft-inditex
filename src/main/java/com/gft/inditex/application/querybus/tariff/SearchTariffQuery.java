package com.gft.inditex.application.querybus.tariff;

import com.gft.inditex.application.querybus.shared.Query;
import com.gft.inditex.domain.model.tariff.Tariff;

public class SearchTariffQuery extends Query<Tariff> {
  private final String date;
  private final Integer productId;
  private final Integer brandId;

  public SearchTariffQuery(String date, Integer productId, Integer brandId) {
    this.date = date;
    this.productId = productId;
    this.brandId = brandId;
  }

  public String getDate() {
    return date;
  }

  public Integer getProductId() {
    return productId;
  }

  public Integer getBrandId() {
    return brandId;
  }
}
