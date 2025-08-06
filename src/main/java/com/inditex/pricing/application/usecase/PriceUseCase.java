package com.inditex.pricing.application.usecase;

import com.inditex.pricing.model.PriceResponse;

import java.time.LocalDateTime;

public interface PriceUseCase {
    PriceResponse getPrice(Long brandId, Long productId, LocalDateTime date);
}
