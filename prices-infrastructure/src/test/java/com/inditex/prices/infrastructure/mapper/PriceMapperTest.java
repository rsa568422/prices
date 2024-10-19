package com.inditex.prices.infrastructure.mapper;

import com.inditex.prices.infrastructure.configuration.MapperTestConfig;
import com.inditex.prices.infrastructure.entity.BrandEntity;
import com.inditex.prices.infrastructure.entity.PriceEntity;
import com.inditex.prices.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static com.inditex.prices.infrastructure.Data.AMOUNT;
import static com.inditex.prices.infrastructure.Data.BRAND;
import static com.inditex.prices.infrastructure.Data.BRAND_ID;
import static com.inditex.prices.infrastructure.Data.CURRENCY;
import static com.inditex.prices.infrastructure.Data.END_DATE;
import static com.inditex.prices.infrastructure.Data.FIRST_PRICE;
import static com.inditex.prices.infrastructure.Data.FIRST_PRICE_ENTITY;
import static com.inditex.prices.infrastructure.Data.PRICE_LIST;
import static com.inditex.prices.infrastructure.Data.PRIORITY;
import static com.inditex.prices.infrastructure.Data.PRODUCT;
import static com.inditex.prices.infrastructure.Data.PRODUCT_ID;
import static com.inditex.prices.infrastructure.Data.START_DATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ContextConfiguration(classes = MapperTestConfig.class)
class PriceMapperTest {

    @Autowired
    private PriceMapper priceMapper;

    @Test
    void toModel() {
        // when
        var actual = priceMapper.toModel(FIRST_PRICE_ENTITY);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(FIRST_PRICE, actual)
        );
    }

    @Test
    void toModelNull() {
        assertNull(priceMapper.toModel(null));
    }

    @Test
    void toModelBrandNull() {
        // given
        var entity = new PriceEntity(PRICE_LIST, null, PRODUCT, START_DATE, END_DATE, PRIORITY, AMOUNT, CURRENCY);

        // when
        var actual = priceMapper.toModel(entity);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertNull(actual.getBrandId()),
                () -> assertEquals(PRODUCT_ID, actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(PRIORITY, actual.getPriority()),
                () -> assertEquals(AMOUNT, actual.getAmount()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void toModelBrandIdNull() {
        // given
        var entity = new PriceEntity(
                PRICE_LIST,
                new BrandEntity(null, "ZARA"),
                PRODUCT,
                START_DATE,
                END_DATE,
                PRIORITY,
                AMOUNT,
                CURRENCY
        );

        // when
        var actual = priceMapper.toModel(entity);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertNull(actual.getBrandId()),
                () -> assertEquals(PRODUCT_ID, actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(PRIORITY, actual.getPriority()),
                () -> assertEquals(AMOUNT, actual.getAmount()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void toModelProductNull() {
        // given
        var entity = new PriceEntity(PRICE_LIST, BRAND, null, START_DATE, END_DATE, PRIORITY, AMOUNT, CURRENCY);

        // when
        var actual = priceMapper.toModel(entity);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(BRAND_ID, actual.getBrandId()),
                () -> assertNull(actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(PRIORITY, actual.getPriority()),
                () -> assertEquals(AMOUNT, actual.getAmount()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void toModelProductIdNull() {
        // given
        var entity = new PriceEntity(
                PRICE_LIST,
                BRAND,
                new ProductEntity(null, "Product A"),
                START_DATE,
                END_DATE,
                PRIORITY,
                AMOUNT,
                CURRENCY
        );

        // when
        var actual = priceMapper.toModel(entity);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(BRAND_ID, actual.getBrandId()),
                () -> assertNull(actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(PRIORITY, actual.getPriority()),
                () -> assertEquals(AMOUNT, actual.getAmount()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }
}