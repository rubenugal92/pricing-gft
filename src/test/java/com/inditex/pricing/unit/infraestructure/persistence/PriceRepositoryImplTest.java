package com.inditex.pricing.unit.infraestructure.persistence;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.persistence.PriceRepositoryImpl;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.persistence.jpa.PriceJpaRepository;
import com.inditex.pricing.infrastructure.persistence.jpa.mapper.PriceEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        date = LocalDateTime.of(2025, 8, 8, 16, 41, 19);
    }

    @Test
    void return_price_test() {
        PriceEntity entity = new PriceEntity();
        Price domainPrice = new Price();
        domainPrice.setBrandId(brandId);
        domainPrice.setProductId(productId);
        domainPrice.setPrice(BigDecimal.valueOf(35.50));

        when(priceJpaRepository.findMatchingPrices(brandId, productId, date))
                .thenReturn(List.of(entity));
        when(priceEntityMapper.toDomain(entity)).thenReturn(domainPrice);

        Optional<Price> result = priceRepository.findPrice(brandId, productId, date);
        assertTrue(result.isPresent());
        assertEquals(domainPrice, result.get());

        verify(priceJpaRepository).findMatchingPrices(brandId, productId, date);
        verify(priceEntityMapper).toDomain(entity);
    }

    @Test
    void return_empty_result_test() {
        when(priceJpaRepository.findMatchingPrices(brandId, productId, date))
                .thenReturn(Collections.emptyList());

        Optional<Price> result = priceRepository.findPrice(brandId, productId, date);

        assertFalse(result.isPresent());

        verify(priceJpaRepository).findMatchingPrices(brandId, productId, date);
        verify(priceEntityMapper, never()).toDomain(any());
    }
}
