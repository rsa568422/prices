package com.inditex.prices.domain.service;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class PriceService {

    private final PriceRepository priceRepository;

    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, OffsetDateTime date) {
        return priceRepository.findApplicablePrices(brandId, productId, date)
                .stream()
                .max(Price::compareTo);
    }
}
