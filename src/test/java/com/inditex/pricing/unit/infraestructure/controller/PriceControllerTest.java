package com.inditex.pricing.unit.infraestructure.controller;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.usecase.PriceUseCase;
import com.inditex.pricing.infrastructure.controller.PriceController;
import com.inditex.pricing.infrastructure.mapper.PriceMapper;
import com.inditex.pricing.model.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceUseCase priceUseCase;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceController priceController;

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private final LocalDateTime date = LocalDateTime.of(2025, 8, 8, 16, 41);


    @Test
    void return_result_if_price_exists_test() {
        Price price = new Price();
        price.setBrandId(brandId);
        price.setProductId(productId);
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        PriceResponse priceResponse = new PriceResponse()
                .brandId(brandId.intValue())
                .productId(productId.intValue())
                .price(30.5);
        when(priceUseCase.getPrice(brandId, productId, date)).thenReturn(price);
        when(priceMapper.toResponse(price)).thenReturn(priceResponse);
        ResponseEntity<PriceResponse> response = priceController.getPrice(brandId.intValue(), productId.intValue(), date);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(priceResponse, response.getBody());
        verify(priceUseCase).getPrice(brandId, productId, date);
        verify(priceMapper).toResponse(price);
    }
}