package com.inditex.prices.infrastructure.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.mapper.PriceMapper;
import com.inditex.prices.infrastructure.repository.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<Price> findApplicablePrices(Integer brandId, Integer productId, OffsetDateTime date) {
        return priceJpaRepository.findByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                .map(priceMapper::toModel)
                .toList();
    }
}
