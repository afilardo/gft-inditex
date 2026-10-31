package com.gft.inditex.infrastructure.persistence.tariff;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.persistence.tariff.mapper.TariffEntityMapper;
import com.gft.inditex.infrastructure.persistence.tariff.model.TariffEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TariffRepositoryImplTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private TariffEntityMapper mapper;

  @InjectMocks
  private TariffRepositoryImpl repository;

  @Test
  void shouldReturnTariffWhenEntityIsFound() {
    TariffEntity entity = Instancio.create(TariffEntity.class);
    Tariff tariff = Instancio.create(Tariff.class);
    SearchTariffCriteria criteria = Instancio.create(SearchTariffCriteria.class);

    TypedQuery<TariffEntity> typedQuery = mock(TypedQuery.class);

    when(entityManager.createQuery(anyString(), eq(TariffEntity.class)))
        .thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.getSingleResultOrNull()).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(tariff);

    Optional<Tariff> result = repository.searchTariff(criteria);

    assertEquals(Optional.of(tariff), result);
    assertTrue(result.isPresent());
    verify(entityManager).createQuery(anyString(), eq(TariffEntity.class));
    verify(typedQuery).setParameter("productId", criteria.productId().get());
    verify(typedQuery).setParameter("brandId", criteria.brandId().get());
    verify(typedQuery).setParameter("date", criteria.date().getLocalDateTime());
    verify(mapper).toDomain(entity);
  }

  @Test
  void shouldReturnEmptyWhenNoEntityIsFound() {
    SearchTariffCriteria criteria = Instancio.create(SearchTariffCriteria.class);

    TypedQuery<TariffEntity> typedQuery = mock(TypedQuery.class);

    when(entityManager.createQuery(anyString(), eq(TariffEntity.class)))
        .thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.getSingleResultOrNull()).thenReturn(null);

    Optional<Tariff> result = repository.searchTariff(criteria);

    assertTrue(result.isEmpty());
    verify(entityManager).createQuery(anyString(), eq(TariffEntity.class));
    verify(typedQuery).setParameter("productId", criteria.productId().get());
    verify(typedQuery).setParameter("brandId", criteria.brandId().get());
    verify(typedQuery).setParameter("date", criteria.date().getLocalDateTime());
    verifyNoInteractions(mapper);
  }
}