package com.gft.inditex.application.querybus.tariff;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.gft.inditex.application.usecases.tariff.SearchTariff;
import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.exceptions.InvalidDate;
import com.gft.inditex.domain.model.tariff.exceptions.MissingBrandId;
import com.gft.inditex.domain.model.tariff.exceptions.MissingDate;
import com.gft.inditex.domain.model.tariff.exceptions.MissingProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchTariffQueryHandlerTest {
  @Mock
  private SearchTariff useCase;

  @InjectMocks
  private SearchTariffQueryHandler handler;

  @Test
  void shouldExecuteUseCaseWhenQueryIsValid() {
    String date = "2026-11-01T14:00:00";
    Integer productId = 1;
    Integer brandId = 1;
    SearchTariffQuery query = new SearchTariffQuery(date, productId, brandId);

    assertDoesNotThrow(() -> {
      handler.handle(query);
    });

    verify(useCase).execute(any(SearchTariffCriteria.class));
  }

  @Test
  void shouldThrowExceptionWhenDateIsMissing() {
    String date = null;
    Integer productId = 1;
    Integer brandId = 1;
    SearchTariffQuery query = new SearchTariffQuery(date, productId, brandId);

    assertThrows(MissingDate.class, () -> {
      handler.handle(query);
    });

    verifyNoInteractions(useCase);
  }

  @Test
  void shouldThrowExceptionWhenDateIsInvalid() {
    String date = "invalid-date";
    Integer productId = 1;
    Integer brandId = 1;
    SearchTariffQuery query = new SearchTariffQuery(date, productId, brandId);

    assertThrows(InvalidDate.class, () -> {
      handler.handle(query);
    });

    verifyNoInteractions(useCase);
  }

  @Test
  void shouldThrowExceptionWhenProductIdIsMissing() {
    String date = "2026-11-01T14:00:00";
    Integer productId = null;
    Integer brandId = 1;
    SearchTariffQuery query = new SearchTariffQuery(date, productId, brandId);

    assertThrows(MissingProductId.class, () -> {
      handler.handle(query);
    });

    verifyNoInteractions(useCase);
  }

  @Test
  void shouldThrowExceptionWhenBrandIdIsMissing() {
    String date = "2026-11-01T14:00:00";
    Integer productId = 1;
    Integer brandId = null;
    SearchTariffQuery query = new SearchTariffQuery(date, productId, brandId);

    assertThrows(MissingBrandId.class, () -> {
      handler.handle(query);
    });

    verifyNoInteractions(useCase);
  }
}