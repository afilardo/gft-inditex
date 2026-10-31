package com.gft.inditex.infrastructure.rest.tariff.mapper;

import com.gft.inditex.domain.common.datetime.DateTime;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class TariffMapper {
  public TariffResponse map(Tariff source) {
    return new TariffResponse(
        source.getProductId().get(),
        source.getBrandId().get(),
        source.getId().get(),
        formatDateTime(source.getStartDate().get()),
        formatDateTime(source.getEndDate().get()),
        source.getPrice().toString());
  }

  private static String formatDateTime(DateTime input) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return input.getLocalDateTime().format(formatter);
  }
}
