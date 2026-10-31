package com.gft.inditex.application.querybus.tariff;

import com.gft.inditex.application.querybus.shared.QueryHandler;
import com.gft.inditex.application.usecases.tariff.SearchTariff;
import com.gft.inditex.domain.common.datetime.DateTime;
import com.gft.inditex.domain.common.datetime.exceptions.DateTimeFormatIsNotIsoStandard;
import com.gft.inditex.domain.common.exceptions.DomainError;
import com.gft.inditex.domain.model.tariff.BrandId;
import com.gft.inditex.domain.model.tariff.ProductId;
import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.domain.model.tariff.exceptions.InvalidDate;
import com.gft.inditex.domain.model.tariff.exceptions.MissingBrandId;
import com.gft.inditex.domain.model.tariff.exceptions.MissingDate;
import com.gft.inditex.domain.model.tariff.exceptions.MissingProductId;

public class SearchTariffQueryHandler implements QueryHandler<Tariff, SearchTariffQuery> {

  private final SearchTariff searchTariff;

  public SearchTariffQueryHandler(SearchTariff searchTariff) {
    this.searchTariff = searchTariff;
  }

  @Override
  public Tariff handle(SearchTariffQuery query) throws DomainError {
    DateTime date = parseDate(query.getDate());
    ProductId productId = parseProductId(query.getProductId());
    BrandId brandId = parseBrandId(query.getBrandId());

    SearchTariffCriteria criteria = new SearchTariffCriteria(date, productId, brandId);

    return searchTariff.execute(criteria);
  }

  private static DateTime parseDate(String date) {
    if (date == null || date.isBlank()) {
      throw new MissingDate();
    } else {
      try {
        return DateTime.parse(date);
      } catch (DateTimeFormatIsNotIsoStandard ex) {
        throw new InvalidDate();
      }
    }
  }

  private static ProductId parseProductId(Integer productId) {
    if (productId == null) {
      throw new MissingProductId();
    }
    return ProductId.create(productId);
  }

  private static BrandId parseBrandId(Integer brandId) {
    if (brandId == null) {
      throw new MissingBrandId();
    }
    return BrandId.create(brandId);
  }
}
