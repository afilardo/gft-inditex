package com.gft.inditex.infrastructure.rest.tariff.model;

import com.gft.inditex.domain.model.tariff.BrandId;
import com.gft.inditex.domain.model.tariff.Price;
import com.gft.inditex.domain.model.tariff.ProductId;
import com.gft.inditex.domain.model.tariff.TariffEndDate;
import com.gft.inditex.domain.model.tariff.TariffId;
import com.gft.inditex.domain.model.tariff.TariffStartDate;

public record TariffResponse(ProductId productId, BrandId brandId, TariffId tariffId, TariffStartDate startDate,
                             TariffEndDate endDate, Price price) {
}
