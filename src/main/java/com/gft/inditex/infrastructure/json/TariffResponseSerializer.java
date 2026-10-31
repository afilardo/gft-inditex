package com.gft.inditex.infrastructure.json;

import com.gft.inditex.domain.common.datetime.DateTime;
import com.gft.inditex.domain.model.tariff.BrandId;
import com.gft.inditex.domain.model.tariff.Price;
import com.gft.inditex.domain.model.tariff.ProductId;
import com.gft.inditex.domain.model.tariff.TariffEndDate;
import com.gft.inditex.domain.model.tariff.TariffId;
import com.gft.inditex.domain.model.tariff.TariffStartDate;
import com.gft.inditex.infrastructure.rest.tariff.model.TariffResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.boot.jackson.JacksonComponent;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;

@JacksonComponent
public class TariffResponseSerializer {
  static class CustomTariffResponseSerializer extends ValueSerializer<TariffResponse> {

    @Override
    public void serialize(TariffResponse value, JsonGenerator generator, SerializationContext context)
        throws JacksonException {
      generator.writeStartObject();
      generator.writeNumberProperty("productId", value.productId().get());
      generator.writeNumberProperty("brandId", value.brandId().get());
      generator.writeNumberProperty("tariffId", value.tariffId().get());
      generator.writeStringProperty("startDate", value.startDate().get().toString());
      generator.writeStringProperty("endDate", value.endDate().get().toString());
      generator.writeStringProperty("price", value.price().get().toString());
      generator.writeEndObject();
    }

    static class CustomTariffResponseDeserializer extends ValueDeserializer<TariffResponse> {

      @Override
      public TariffResponse deserialize(JsonParser parser, DeserializationContext context) throws JacksonException {
        JsonNode rootNode = context.readTree(parser);

        String[] parts = rootNode.get("price").asString().trim().split("\\s+", 2);

        BigDecimal amount = new BigDecimal(parts[0]).setScale(2, RoundingMode.HALF_EVEN);
        String currency = parts[1];

        return new TariffResponse(
            ProductId.create(rootNode.path("productId").asInt()),
            BrandId.create(rootNode.path("brandId").asInt()),
            TariffId.create(rootNode.path("tariffId").asInt()),
            TariffStartDate.create(DateTime.parse(rootNode.path("startDate").asString())),
            TariffEndDate.create(DateTime.parse(rootNode.path("endDate").asString())),
            Price.create(amount, currency));
      }
    }
  }
}