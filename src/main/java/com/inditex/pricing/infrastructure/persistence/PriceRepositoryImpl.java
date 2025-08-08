package com.inditex.pricing.infrastructure.persistence;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.repository.PriceRepository;
import com.inditex.pricing.infrastructure.persistence.jpa.mapper.PriceEntityMapper;
import com.inditex.pricing.infrastructure.persistence.jpa.PriceJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class PriceRepositoryImpl implements PriceRepository {
    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Optional<Price> findPrice(Long brandId, Long productId, LocalDateTime date) {
       return priceJpaRepository.findMatchingPrices(brandId, productId, date)
                .stream()
                .findFirst()
                .map(priceEntityMapper::toDomain);
    }
}