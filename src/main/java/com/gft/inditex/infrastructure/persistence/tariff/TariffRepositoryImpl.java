package com.gft.inditex.infrastructure.persistence.tariff;

import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.domain.model.tariff.TariffRepository;
import com.gft.inditex.infrastructure.persistence.tariff.mapper.TariffEntityMapper;
import com.gft.inditex.infrastructure.persistence.tariff.model.TariffEntity;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TariffRepositoryImpl implements TariffRepository {

  private final EntityManager entityManager;

  private final TariffEntityMapper mapper;

  public TariffRepositoryImpl(EntityManager entityManager, TariffEntityMapper mapper) {
    this.entityManager = entityManager;
    this.mapper = mapper;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Tariff> searchTariff(SearchTariffCriteria criteria) {

    String query = """
        SELECT t FROM TariffEntity t WHERE t.productId = :productId  AND t.brandId = :brandId
        AND t.startDate <= :date AND t.endDate >= :date
        ORDER BY t.priority DESC LIMIT 1""";

    TariffEntity tariffEntity = entityManager
        .createQuery(query, TariffEntity.class)
        .setParameter("productId", criteria.productId().get())
        .setParameter("brandId", criteria.brandId().get())
        .setParameter("date", criteria.date().getLocalDateTime())
        .getSingleResultOrNull();

    return Optional.ofNullable(tariffEntity)
        .map(mapper::toDomain);
  }
}
