package com.gft.inditex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureRestTestClient
public class SearchTariffIntegrationTest {

  private static final String URI = "/api/v1/tariffs";

  @Autowired
  RestTestClient client;

  @ParameterizedTest(name = "date: {0}, productId: {1}, brand: {2}, tariffId: {3}")
  @CsvSource({
      "2020-06-14T10:00:00, 35455, 1, 1",
      "2020-06-14T16:00:00, 35455, 1, 2",
      "2020-06-14T21:00:00, 35455, 1, 1",
      "2020-06-15T10:00:00, 35455, 1, 3",
      "2020-06-16T21:00:00, 35455, 1, 4"
  })
  void searchTariffTest(String date, int productId, int brandId, int tariffId) {

    var result = client.get()
        .uri(builder -> builder
            .path(URI)
            .queryParam("date", date)
            .queryParam("productId", productId)
            .queryParam("brandId", brandId)
            .build())
        .exchange()
        .expectStatus().isOk()
        .returnResult(TariffResponse.class);

    assertNotNull(result);
    assertNotNull(result.getResponseBody());
    assertEquals(tariffId, result.getResponseBody().tariffId());
  }
}
