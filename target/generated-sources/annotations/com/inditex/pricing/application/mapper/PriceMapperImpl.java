package com.inditex.pricing.application.mapper;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.model.PriceResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-07T01:05:35+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceResponse toResponse(Price priceDomain) {
        if ( priceDomain == null ) {
            return null;
        }

        PriceResponse priceResponse = new PriceResponse();

        if ( priceDomain.getProductId() != null ) {
            priceResponse.setProductId( priceDomain.getProductId().intValue() );
        }
        if ( priceDomain.getBrandId() != null ) {
            priceResponse.setBrandId( priceDomain.getBrandId().intValue() );
        }
        priceResponse.setPriceList( priceDomain.getPriceList() );
        priceResponse.setStartDate( priceDomain.getStartDate() );
        priceResponse.setEndDate( priceDomain.getEndDate() );
        priceResponse.setPrice( priceDomain.getPrice() );

        return priceResponse;
    }
}
