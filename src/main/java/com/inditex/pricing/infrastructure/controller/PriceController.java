package com.inditex.pricing.infrastructure.controller;


import com.inditex.pricing.api.PricingApi;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.usecase.PriceUseCase;
import com.inditex.pricing.infrastructure.mapper.PriceMapper;
import com.inditex.pricing.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricingApi {

    private final PriceUseCase priceUseCase;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer brandId, Integer productId, LocalDateTime date) {
        return ResponseEntity.ok(priceMapper.toResponse(priceUseCase.getPrice(
                brandId.longValue(),
                productId.longValue(),
                date)));
    }
}