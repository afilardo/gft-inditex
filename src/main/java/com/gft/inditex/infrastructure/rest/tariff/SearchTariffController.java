package com.gft.inditex.infrastructure.rest.tariff;

import com.gft.inditex.application.querybus.shared.QueryBus;
import com.gft.inditex.application.querybus.tariff.SearchTariffQuery;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.mapper.TariffMapper;
import com.gft.inditex.infrastructure.rest.tariff.model.SearchTariffRequest;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchTariffController {
  private final QueryBus queryBus;
  private final TariffMapper mapper;

  public SearchTariffController(QueryBus queryBus, TariffMapper mapper) {
    this.queryBus = queryBus;
    this.mapper = mapper;
  }

  @GetMapping(value = "{version}/tariffs", version = "1.0")
  public ResponseEntity<TariffResponse> searchPrices(SearchTariffRequest request) {

    SearchTariffQuery query = new SearchTariffQuery(
        request.date(),
        request.productId(),
        request.brandId()
    );

    Tariff tariff = queryBus.handle(query);
    return ResponseEntity.ok(mapper.map(tariff));
  }
}
