package com.inditex.prices.infrastructure;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.entity.BrandEntity;
import com.inditex.prices.infrastructure.entity.PriceEntity;
import com.inditex.prices.infrastructure.entity.ProductEntity;

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

    public static final Price FIRST_PRICE = Price.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .priceList(PRICE_LIST)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .priority(PRIORITY)
            .amount(AMOUNT)
            .currency(CURRENCY)
            .build();

    public static final Price SECOND_PRICE = Price.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .priceList(2)
            .startDate(OffsetDateTime.of(DATE, LocalTime.of(15, 0, 0), ZoneOffset.UTC))
            .endDate(OffsetDateTime.of(DATE, LocalTime.of(18, 30, 0), ZoneOffset.UTC))
            .priority(1)
            .amount(BigDecimal.valueOf(25.45))
            .currency(CURRENCY)
            .build();

    public static final BrandEntity BRAND = new BrandEntity(1, "ZARA");

    public static final ProductEntity PRODUCT = new ProductEntity(35455, "Product A");

    public static final PriceEntity FIRST_PRICE_ENTITY =
            new PriceEntity(PRICE_LIST, BRAND, PRODUCT, START_DATE, END_DATE, PRIORITY, AMOUNT, CURRENCY);

    public static final PriceEntity SECOND_PRICE_ENTITY = new PriceEntity(
            2,
            BRAND,
            PRODUCT,
            OffsetDateTime.of(DATE, LocalTime.of(15, 0, 0), ZoneOffset.UTC),
            OffsetDateTime.of(DATE, LocalTime.of(18, 30, 0), ZoneOffset.UTC),
            1,
            BigDecimal.valueOf(25.45),
            CURRENCY
    );
}
