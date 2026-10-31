package com.gft.inditex.domain.model.tariff;

import com.gft.inditex.domain.common.datetime.DateTime;

public record SearchTariffCriteria(DateTime date, ProductId productId, BrandId brandId) {
}
