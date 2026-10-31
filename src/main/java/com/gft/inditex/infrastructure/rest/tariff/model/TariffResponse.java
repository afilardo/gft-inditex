package com.gft.inditex.infrastructure.rest.tariff.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record TariffResponse(@Schema(description = "Product ID", example = "35455") int productId,
                             @Schema(description = "Brand ID (ZARA=1, ..)", example = "1") int brandId,
                             @Schema(description = "Tariff ID", example = "1") int tariffId,
                             @Schema(description = "Tariff start date", example = "2020-06-14 00:00") String startDate,
                             @Schema(description = "Tariff end date", example = "2020-12-31 23:59:59") String endDate,
                             @Schema(description = "Tariff price", example = "35.50 EUR") String price) {
}
