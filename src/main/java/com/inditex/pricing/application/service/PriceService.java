package com.inditex.pricing.application.service;


import com.inditex.pricing.application.exception.PriceNotFoundException;
import com.inditex.pricing.application.mapper.PriceMapper;
import com.inditex.pricing.application.usecase.PriceUseCase;
import com.inditex.pricing.domain.repository.PriceRepository;
import com.inditex.pricing.model.PriceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PriceService implements PriceUseCase {

    private final PriceRepository repository;
    private final PriceMapper priceMapper;

    @Override
    public PriceResponse getPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceMapper.toResponse(
                repository.findPrice(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No price found for brandId=%d, productId=%d at date=%s", brandId, productId, date)
                )));
    }
}