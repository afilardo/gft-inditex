package com.gft.inditex.infrastructure.persistence.tariff.mapper;

import com.gft.inditex.domain.common.datetime.DateTime;
import com.gft.inditex.domain.model.tariff.BrandId;
import com.gft.inditex.domain.model.tariff.Price;
import com.gft.inditex.domain.model.tariff.ProductId;
import com.gft.inditex.domain.model.tariff.Tariff;
import com.gft.inditex.domain.model.tariff.TariffEndDate;
import com.gft.inditex.domain.model.tariff.TariffId;
import com.gft.inditex.domain.model.tariff.TariffPriority;
import com.gft.inditex.domain.model.tariff.TariffStartDate;
import com.gft.inditex.infrastructure.persistence.tariff.model.TariffEntity;
import org.springframework.stereotype.Component;

@Component
public class TariffEntityMapper {

  public Tariff toDomain(TariffEntity tariffEntity) {
    return Tariff.create(
        TariffId.create(tariffEntity.getId()),
        BrandId.create(tariffEntity.getBrandId()),
        TariffStartDate.create(DateTime.parse(tariffEntity.getStartDate().toString())),
        TariffEndDate.create(DateTime.parse(tariffEntity.getEndDate().toString())),
        ProductId.create(tariffEntity.getProductId()),
        TariffPriority.create(tariffEntity.getPriority()),
        Price.create(tariffEntity.getAmount(), tariffEntity.getCurrency())
    );
  }
}
