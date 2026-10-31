package com.gft.inditex.infrastructure.rest.tariff.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TariffMapperTest {

  @InjectMocks
  private TariffMapper mapper;

  @Test
  void shouldReturnSameValues() {
    Tariff source = Instancio.create(Tariff.class);
    TariffResponse result = mapper.map(source);

    assertEquals(source.getProductId(), result.productId());
    assertEquals(source.getBrandId(), result.brandId());
    assertEquals(source.getId(), result.tariffId());
    assertEquals(source.getStartDate(), result.startDate());
    assertEquals(source.getEndDate(), result.endDate());
    assertEquals(source.getPrice(), result.price());
  }
}