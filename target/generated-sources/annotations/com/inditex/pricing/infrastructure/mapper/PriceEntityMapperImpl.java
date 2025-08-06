package com.inditex.pricing.infrastructure.mapper;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T01:36:27+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PriceEntityMapperImpl implements PriceEntityMapper {

    @Override
    public Price toDomain(PriceEntity priceEntity) {
        if ( priceEntity == null ) {
            return null;
        }

        Price price = new Price();

        price.setBrandId( priceEntity.getBrandId() );
        price.setProductId( priceEntity.getProductId() );
        price.setStartDate( priceEntity.getStartDate() );
        price.setEndDate( priceEntity.getEndDate() );
        price.setPriceList( priceEntity.getPriceList() );
        price.setPriority( priceEntity.getPriority() );
        if ( priceEntity.getPrice() != null ) {
            price.setPrice( priceEntity.getPrice().doubleValue() );
        }
        price.setCurrency( priceEntity.getCurrency() );

        return price;
    }
}
