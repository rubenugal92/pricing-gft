package com.inditex.pricing.infrastructure.controller;


import com.inditex.pricing.api.PricingApi;
import com.inditex.pricing.application.usecase.PriceUseCase;
import com.inditex.pricing.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricingApi {

    private final PriceUseCase priceUseCase;
    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer brandId, Integer productId, LocalDateTime date) {
        return ResponseEntity.ok(priceUseCase.getPrice(brandId.longValue(),productId.longValue(),date));
    }
}
