package com.gft.inditex.infrastructure.persistence.tariff.mapper;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.persistence.tariff.model.TariffEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TariffEntityMapperTest {

  @InjectMocks
  private TariffEntityMapper mapper;

  @Test
  void shouldReturnSameValues() {
    TariffEntity source = Instancio.of(TariffEntity.class)
        .set(field(TariffEntity.class, "currency"), "USD")
        .create();

    Tariff result = mapper.toDomain(source);

    assertEquals(source.getId(), result.getId().get());
    assertEquals(source.getBrandId(), result.getBrandId().get());
    assertEquals(source.getStartDate(), result.getStartDate().get().getLocalDateTime());
    assertEquals(source.getEndDate(), result.getEndDate().get().getLocalDateTime());
    assertEquals(source.getProductId(), result.getProductId().get());
    assertEquals(source.getPriority(), result.getPriority().get());
    assertEquals(source.getAmount(), result.getPrice().get().getAmount());
    assertEquals(source.getCurrency(), result.getPrice().get().getCurrency().getCurrencyCode());
  }
}