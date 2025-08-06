package com.inditex.pricing.infrastructure.persistence;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.repository.PriceRepository;
import com.inditex.pricing.infrastructure.mapper.PriceEntityMapper;
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
        Optional<Price> response = priceJpaRepository.findMatchingPrices(brandId, productId, date)
                .stream()
                .findFirst()
                .map(priceEntityMapper::toDomain);
        return response;
    }

        //A pesar de que la lógica se suela poner en el service, si es por simplemente filtrar la query lo veo bien ponerlo aquí
        //ya que el findFirst en realidad sería como poner el típico LIMIT 1 en SQL o top1 en jpa. Si fuese algo más complejo si que lo movería al service.
}