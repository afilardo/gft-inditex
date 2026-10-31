package com.gft.inditex.infrastructure.rest.tariff.mapper;

import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import org.springframework.stereotype.Component;

@Component
public class TariffMapper {
  public TariffResponse map(Tariff source) {
    return new TariffResponse(
        source.getProductId(),
        source.getBrandId(),
        source.getId(),
        source.getStartDate(),
        source.getEndDate(),
        source.getPrice());
  }
}
