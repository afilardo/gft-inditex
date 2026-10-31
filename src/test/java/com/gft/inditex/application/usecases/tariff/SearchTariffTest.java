package com.gft.inditex.application.usecases.tariff;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.domain.model.tariff.TariffRepository;
import com.gft.inditex.domain.model.tariff.exceptions.TariffNotFound;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchTariffTest {

  @Mock
  private TariffRepository repository;

  @InjectMocks
  private SearchTariff searchTariff;

  @Test
  void shouldReturnTariffWhenTariffIsFound() {
    when(repository.searchTariff(any(SearchTariffCriteria.class))).thenReturn(Optional.of(Instancio.create(Tariff.class)));

    assertDoesNotThrow(() -> {
      SearchTariffCriteria criteria = Instancio.create(SearchTariffCriteria.class);
      Tariff result = searchTariff.execute(criteria);

      assertNotNull(result);
      verify(repository).searchTariff(criteria);
    });
  }

  @Test
  void shouldThrowAnExceptionWhenTariffIsNotFound() {
    when(repository.searchTariff(any(SearchTariffCriteria.class))).thenReturn(Optional.empty());

    assertThrows(TariffNotFound.class, () -> {
      SearchTariffCriteria criteria = Instancio.create(SearchTariffCriteria.class);
      Tariff result = searchTariff.execute(criteria);

      assertNull(result);
      verify(repository).searchTariff(criteria);
    });
  }
}