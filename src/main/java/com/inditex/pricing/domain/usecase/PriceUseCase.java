package com.inditex.pricing.domain.usecase;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.model.PriceResponse;

import java.time.LocalDateTime;

public interface PriceUseCase {
    Price getPrice(Long brandId, Long productId, LocalDateTime date);
}