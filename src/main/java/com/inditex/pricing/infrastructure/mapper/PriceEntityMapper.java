package com.inditex.pricing.infrastructure.mapper;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toDomain (PriceEntity priceEntity);
}
