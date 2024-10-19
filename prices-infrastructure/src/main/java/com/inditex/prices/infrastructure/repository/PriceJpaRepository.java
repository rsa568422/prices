package com.inditex.prices.infrastructure.repository;

import com.inditex.prices.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(PriceQuery.FIND_BY_BRAND_ID_AND_PRODUCT_ID_AND_DATE_QUERY)
    List<PriceEntity> findByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, OffsetDateTime date);
}
