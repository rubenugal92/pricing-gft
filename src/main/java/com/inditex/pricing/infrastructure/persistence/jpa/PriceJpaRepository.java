package com.inditex.pricing.infrastructure.persistence.jpa;

import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE " +
            "p.brandId = :brandId AND " +
            "p.productId = :productId AND " +
            ":date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findMatchingPrices(@Param("brandId") Long brandId,
                                         @Param("productId") Long productId,
                                         @Param("date") LocalDateTime date);
}
