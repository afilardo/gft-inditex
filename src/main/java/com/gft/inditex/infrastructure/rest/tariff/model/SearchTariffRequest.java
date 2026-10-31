package com.gft.inditex.infrastructure.rest.tariff.model;

import io.swagger.v3.oas.annotations.Parameter;

public record SearchTariffRequest(
    @Parameter(description = "Date", required = true, example = "2020-06-14T10:00:00") String date,
    @Parameter(description = "Product ID", required = true, example = "35455") Integer productId,
    @Parameter(description = "Brand ID (ZARA=1, ..)", required = true, example = "1") Integer brandId) {
}