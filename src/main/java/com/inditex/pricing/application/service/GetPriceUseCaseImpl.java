package com.inditex.pricing.application.service;


import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.usecase.PriceUseCase;
import com.inditex.pricing.domain.repository.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class GetPriceUseCaseImpl implements PriceUseCase {

    private final PriceRepository repository;
    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        return repository.findPrice(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No price found for brandId=%d, productId=%d at date=%s", brandId, productId, date)
                ));
    }
}