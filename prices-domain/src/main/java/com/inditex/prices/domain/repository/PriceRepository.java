package com.inditex.prices.domain.repository;

import com.inditex.prices.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findApplicablePrices(Integer brandId, Integer productId, OffsetDateTime date);
}
