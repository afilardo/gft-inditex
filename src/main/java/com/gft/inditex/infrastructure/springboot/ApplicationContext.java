package com.gft.inditex.infrastructure.springboot;

import com.gft.inditex.application.querybus.shared.QueryHandler;
import com.gft.inditex.application.querybus.tariff.SearchTariffQueryHandler;
import com.gft.inditex.application.usecases.tariff.SearchTariff;
import com.gft.inditex.domain.model.tariff.TariffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

  @Bean
  public SearchTariff searchTariff(TariffRepository tariffRepository) {
    return new SearchTariff(tariffRepository);
  }

  @Bean
  public QueryHandler searchTariffQueryHandler(SearchTariff searchTariff) {
    return new SearchTariffQueryHandler(searchTariff);
  }
}
