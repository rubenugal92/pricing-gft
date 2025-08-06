package com.inditex.pricing.application.mapper;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceResponse toResponse(Price priceDomain);


}