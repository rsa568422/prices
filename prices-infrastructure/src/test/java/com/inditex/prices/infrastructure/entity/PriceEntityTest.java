package com.inditex.prices.infrastructure.entity;

import com.inditex.prices.infrastructure.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PriceEntityTest {

    @Test
    void testAllArgsConstructor() {
        // when
        var actual = new PriceEntity(
                Data.PRICE_LIST,
                Data.BRAND,
                Data.PRODUCT,
                Data.START_DATE,
                Data.END_DATE,
                Data.PRIORITY,
                Data.AMOUNT,
                Data.CURRENCY
        );

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND, actual.getBrand()),
                () -> assertEquals(Data.PRODUCT, actual.getProduct()),
                () -> assertEquals(Data.PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(Data.START_DATE, actual.getStartDate()),
                () -> assertEquals(Data.END_DATE, actual.getEndDate()),
                () -> assertEquals(Data.PRIORITY, actual.getPriority()),
                () -> assertEquals(Data.AMOUNT, actual.getPrice()),
                () -> assertEquals(Data.CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void testSetters() {
        // given
        var actual = new PriceEntity();

        // when
        actual.setPriceList(Data.PRICE_LIST);
        actual.setBrand(Data.BRAND);
        actual.setProduct(Data.PRODUCT);
        actual.setStartDate(Data.START_DATE);
        actual.setEndDate(Data.END_DATE);
        actual.setPriority(Data.PRIORITY);
        actual.setPrice(Data.AMOUNT);
        actual.setCurrency(Data.CURRENCY);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND, actual.getBrand()),
                () -> assertEquals(Data.PRODUCT, actual.getProduct()),
                () -> assertEquals(Data.PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(Data.START_DATE, actual.getStartDate()),
                () -> assertEquals(Data.END_DATE, actual.getEndDate()),
                () -> assertEquals(Data.PRIORITY, actual.getPriority()),
                () -> assertEquals(Data.AMOUNT, actual.getPrice()),
                () -> assertEquals(Data.CURRENCY, actual.getCurrency())
        );
    }
}