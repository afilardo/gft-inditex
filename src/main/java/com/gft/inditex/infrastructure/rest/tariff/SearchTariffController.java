package com.gft.inditex.infrastructure.rest.tariff;

import com.gft.inditex.application.querybus.shared.QueryBus;
import com.gft.inditex.application.querybus.tariff.SearchTariffQuery;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.infrastructure.rest.tariff.mapper.TariffMapper;
import com.gft.inditex.infrastructure.rest.tariff.model.SearchTariffRequest;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "GFT - Inditex API", description = "API for search tariff")
public class SearchTariffController {
  private final QueryBus queryBus;
  private final TariffMapper mapper;

  public SearchTariffController(QueryBus queryBus, TariffMapper mapper) {
    this.queryBus = queryBus;
    this.mapper = mapper;
  }

  @Operation(summary = "Search for a tariff for the given date/productId/brandId combination")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the tariff",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = TariffResponse.class)) }),
      @ApiResponse(responseCode = "404", description = "Tariff not found",
          content = @Content) })
  @GetMapping(value = "{version}/tariffs", version = "1.0")
  public ResponseEntity<TariffResponse> searchTariff(@ParameterObject SearchTariffRequest request) {

    SearchTariffQuery query = new SearchTariffQuery(
        request.date(),
        request.productId(),
        request.brandId()
    );

    Tariff tariff = queryBus.handle(query);
    return ResponseEntity.ok(mapper.map(tariff));
  }
}
