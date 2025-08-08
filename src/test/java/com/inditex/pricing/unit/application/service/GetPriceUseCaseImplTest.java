package com.inditex.pricing.unit.application.service;

import com.inditex.pricing.application.service.GetPriceUseCaseImpl;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseImplTest {
    @Mock
    private PriceRepository repository;
    @InjectMocks
    private GetPriceUseCaseImpl getPriceUseCase;

    @Test
    void return_price_when_price_exists_test() {
        Long brandId = 1L;
        Long productId = 10L;
        LocalDateTime date = LocalDateTime.now();

        Price price = new Price();
        when(repository.findPrice(brandId, productId, date)).thenReturn(Optional.of(price));
        Price result = getPriceUseCase.getPrice(brandId, productId, date);
        assertEquals(price, result);
        verify(repository, times(1)).findPrice(brandId, productId, date);
    }

    @Test
    void price_not_found_test() {
        Long brandId = 1L;
        Long productId = 10L;
        LocalDateTime date = LocalDateTime.now();

        when(repository.findPrice(brandId, productId, date)).thenReturn(Optional.empty());
        PriceNotFoundException ex = assertThrows(PriceNotFoundException.class, () -> {
            getPriceUseCase.getPrice(brandId, productId, date);
        });
        assertTrue(ex.getMessage().contains("No price found for brandId"));
        verify(repository, times(1)).findPrice(brandId, productId, date);
    }
}