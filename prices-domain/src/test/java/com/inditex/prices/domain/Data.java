package com.inditex.prices.domain;

import com.inditex.prices.domain.model.Price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class Data {

    private Data() { }

    private static final LocalDate DATE = LocalDate.of(2020, Month.JUNE, 14);

    public static final Integer BRAND_ID = 1;

    public static final Integer PRODUCT_ID = 35455;

    public static final Integer PRICE_LIST = 1;

    public static final OffsetDateTime START_DATE = OffsetDateTime.of(DATE, LocalTime.MIN, ZoneOffset.UTC);

    public static final OffsetDateTime END_DATE =
            OffsetDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.MAX, ZoneOffset.UTC);

    public static final Integer PRIORITY = 0;

    public static final BigDecimal AMOUNT = BigDecimal.valueOf(35.50);

    public static final String CURRENCY = "EUR";

    public static final Price.PriceBuilder BUILDER = Price.builder()
            .brandId(Data.BRAND_ID)
            .productId(Data.PRODUCT_ID)
            .priceList(Data.PRICE_LIST)
            .startDate(Data.START_DATE)
            .endDate(Data.END_DATE)
            .priority(Data.PRIORITY)
            .amount(Data.AMOUNT)
            .currency(Data.CURRENCY);

    public static final Price SECOND_PRICE = Price.builder()
            .brandId(Data.BRAND_ID)
            .productId(Data.PRODUCT_ID)
            .priceList(2)
            .startDate(OffsetDateTime.of(DATE, LocalTime.of(15, 0, 0), ZoneOffset.UTC))
            .endDate(OffsetDateTime.of(DATE, LocalTime.of(18, 30, 0), ZoneOffset.UTC))
            .priority(1)
            .amount(BigDecimal.valueOf(25.45))
            .currency(Data.CURRENCY)
            .build();
}
