package com.gft.inditex.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class TariffSteps {
  private static final String TARIFFS_URI = "/api/v1/tariffs";

  @Autowired
  private RestTestClient client;

  @Autowired
  private ObjectMapper objectMapper;

  private EntityExchangeResult<String> response;

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
        .returnResult(String.class);
  }

  @Then("response code is {int}")
  public void checkHttpResponseCode(int expectedStatus) {
    assertEquals(expectedStatus, response.getStatus().value());
  }

  @And("the response body contains")
  public void checkResponseBody(DataTable table) throws Exception {
    JsonNode json = objectMapper.readTree(response.getResponseBody());
    Map<String, String> expected = table.asMap(String.class, String.class);

    assertNotNull(json);
    assertEquals(Integer.parseInt(expected.get("productId")), json.get("productId").asInt());
    assertEquals(Integer.parseInt(expected.get("brandId")), json.get("brandId").asInt());
    assertEquals(Integer.parseInt(expected.get("tariffId")), json.get("tariffId").asInt());
    assertEquals(expected.get("startDate"), json.get("startDate").asString());
    assertEquals(expected.get("endDate"), json.get("endDate").asString());
    assertEquals(expected.get("price"), json.get("price").asString());
  }
}
