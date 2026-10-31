package com.gft.inditex.infrastructure.rest.tariff.model;

public record SearchTariffRequest(String date, Integer productId, Integer brandId) {
}