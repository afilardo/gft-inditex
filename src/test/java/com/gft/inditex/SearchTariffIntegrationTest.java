package com.gft.inditex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SearchTariffIntegrationTest {

  private static final String TARIFFS_URI = "/api/v1/tariffs";

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest(name = "date: {0}, productId: {1}, brand: {2}, tariffId: {3}")
  @CsvSource({
      "2020-06-14T10:00:00, 35455, 1, 1",
      "2020-06-14T16:00:00, 35455, 1, 2",
      "2020-06-14T21:00:00, 35455, 1, 1",
      "2020-06-15T10:00:00, 35455, 1, 3",
      "2020-06-16T21:00:00, 35455, 1, 4"
  })
  void searchTariffTest(String date, String productId, String brandId, String tariffId) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(TARIFFS_URI)
        .param("date", date)
        .param("productId", productId)
        .param("brandId", brandId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.tariffId").value(tariffId));
  }

  @Test
  void searchTariffNotFoundTest() throws Exception {
    String date = "2020-06-14T10:00:00";
    String nonExistingProductId = "0";
    String brandId = "0";

    mockMvc.perform(MockMvcRequestBuilders.get(TARIFFS_URI)
            .param("date", date)
            .param("productId", nonExistingProductId)
            .param("brandId", brandId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
