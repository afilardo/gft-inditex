package com.gft.inditex.application.usecases.tariff;

import com.gft.inditex.domain.model.tariff.SearchTariffCriteria;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.domain.model.tariff.TariffRepository;
import com.gft.inditex.domain.model.tariff.exceptions.TariffNotFound;
import java.util.Optional;

public class SearchTariff {

  private final TariffRepository repository;

  public SearchTariff(TariffRepository repository) {
    this.repository = repository;
  }

  public Tariff execute(SearchTariffCriteria criteria) {
    Optional<Tariff> tariff = repository.searchTariff(criteria);

    return tariff.orElseThrow(TariffNotFound::new);
  }

}
