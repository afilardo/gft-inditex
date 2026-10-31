package com.gft.inditex.infrastructure.rest.tariff.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gft.inditex.domain.common.datetime.DateTime;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import java.time.format.DateTimeFormatter;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class TariffMapperTest {

  @InjectMocks
  private TariffMapper mapper;

  @Test
  void shouldReturnSameValues() {
    Tariff source = Instancio.create(Tariff.class);
    TariffResponse result = mapper.map(source);

    assertEquals(source.getProductId().get(), result.productId());
    assertEquals(source.getBrandId().get(), result.brandId());
    assertEquals(source.getId().get(), result.tariffId());
    assertEquals(formatDateTime(source.getStartDate().get()), result.startDate());
    assertEquals(formatDateTime(source.getEndDate().get()), result.endDate());
    assertEquals(source.getPrice().get().toString(), result.price());
  }

  private static String formatDateTime(DateTime input) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return input.getLocalDateTime().format(formatter);
  }
}