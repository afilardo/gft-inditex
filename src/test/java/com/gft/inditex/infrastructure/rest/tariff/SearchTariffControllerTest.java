package com.gft.inditex.infrastructure.rest.tariff;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gft.inditex.application.querybus.shared.QueryBus;
import com.gft.inditex.application.querybus.tariff.SearchTariffQuery;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.mapper.TariffMapper;
import com.gft.inditex.infrastructure.rest.tariff.model.SearchTariffRequest;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class SearchTariffControllerTest {

  @Mock
  private QueryBus queryBus;

  @Mock
  private TariffMapper mapper;

  @InjectMocks
  private SearchTariffController controller;

  @Test
  void shouldReturnValidResponseWhenRequestIsValid() {
    String date = "2026-11-01T14:00:00";
    Integer productId = 1;
    Integer brandId = 1;

    Tariff tariff = Instancio.create(Tariff.class);
    TariffResponse response = Instancio.create(TariffResponse.class);

    when(queryBus.handle(any(SearchTariffQuery.class))).thenReturn(tariff);
    when(mapper.map(tariff)).thenReturn(response);

    assertDoesNotThrow(() -> {
      SearchTariffRequest request = new SearchTariffRequest(date, productId, brandId);
      ResponseEntity<TariffResponse> result = controller.searchPrices(request);

      assertNotNull(result);
      assertEquals(response, result.getBody());
      assertEquals(HttpStatus.OK, result.getStatusCode());
      verify(queryBus).handle(any(SearchTariffQuery.class));
      verify(mapper).map(tariff);
    });
  }
}