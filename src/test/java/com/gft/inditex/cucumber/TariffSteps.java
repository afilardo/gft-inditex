package com.gft.inditex.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

public class TariffSteps {
  private static final String TARIFFS_URI = "/api/v1/tariffs";

  @Autowired
  private RestTestClient client;

  private EntityExchangeResult<TariffResponse> response;

  @When("I request a tariff for date {string}, product {int}, and brand {int}")
  public void searchTariff(String date, int productId, int brandId) {
    response = client.get()
        .uri(builder -> builder
            .path(TARIFFS_URI)
            .queryParam("date", date)
            .queryParam("productId", productId)
            .queryParam("brandId", brandId)
            .build())
        .exchange()
        .returnResult(TariffResponse.class);
  }

  @Then("response code is {int}")
  public void checkHttpResponseCode(int expectedStatus) {
    assertEquals(expectedStatus, response.getStatus().value());
  }

  @And("the response body contains")
  public void checkResponseBody(DataTable table) {
    TariffResponse tariff = response.getResponseBody();
    Map<String, String> expected = table.asMap(String.class, String.class);

    assertNotNull(tariff);
    assertEquals(Integer.parseInt(expected.get("productId")), tariff.productId());
    assertEquals(Integer.parseInt(expected.get("brandId")), tariff.brandId());
    assertEquals(Integer.parseInt(expected.get("tariffId")), tariff.tariffId());
    assertEquals(expected.get("startDate"), tariff.startDate());
    assertEquals(expected.get("endDate"), tariff.endDate());
    assertEquals(expected.get("price"), tariff.price());
  }
}
