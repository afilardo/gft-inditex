package com.gft.inditex.domain.model.tariff;

import java.util.Optional;

public interface TariffRepository {
  Optional<Tariff> searchTariff(SearchTariffCriteria criteria);
}
