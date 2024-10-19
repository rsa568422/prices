package com.inditex.prices.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class Price implements Comparable<Price> {

    private Integer brandId;

    private Integer productId;

    private Integer priceList;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private Integer priority;

    private BigDecimal amount;

    private String currency;

    @Override
    public int compareTo(Price o) {
        return priority.compareTo(o.getPriority());
    }
}
