package com.inditex.prices.domain.model;

import com.inditex.prices.domain.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriceTest {

    @Test
    void testBuilder() {
        // when
        var actual = Data.BUILDER.build();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND_ID, actual.getBrandId()),
                () -> assertEquals(Data.PRODUCT_ID, actual.getProductId()),
                () -> assertEquals(Data.PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(Data.START_DATE, actual.getStartDate()),
                () -> assertEquals(Data.END_DATE, actual.getEndDate()),
                () -> assertEquals(Data.PRIORITY, actual.getPriority()),
                () -> assertEquals(Data.AMOUNT, actual.getAmount()),
                () -> assertEquals(Data.CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void testSetters() {
        // when
        var actual = Price.builder().build();
        actual.setBrandId(Data.BRAND_ID);
        actual.setProductId(Data.PRODUCT_ID);
        actual.setPriceList(Data.PRICE_LIST);
        actual.setStartDate(Data.START_DATE);
        actual.setEndDate(Data.END_DATE);
        actual.setPriority(Data.PRIORITY);
        actual.setAmount(Data.AMOUNT);
        actual.setCurrency(Data.CURRENCY);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND_ID, actual.getBrandId()),
                () -> assertEquals(Data.PRODUCT_ID, actual.getProductId()),
                () -> assertEquals(Data.PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(Data.START_DATE, actual.getStartDate()),
                () -> assertEquals(Data.END_DATE, actual.getEndDate()),
                () -> assertEquals(Data.PRIORITY, actual.getPriority()),
                () -> assertEquals(Data.AMOUNT, actual.getAmount()),
                () -> assertEquals(Data.CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void testCompareTo() {
        // given
        var lowPriority = Data.BUILDER.build();
        lowPriority.setPriority(-1);
        var highPriority = Data.BUILDER.build();
        highPriority.setPriority(1);

        // when
        var actual = Data.BUILDER.build();

        // then
        assertAll(
                () -> assertEquals(0, Data.BUILDER.build().compareTo(actual)),
                () -> assertEquals(-1, lowPriority.compareTo(actual)),
                () -> assertEquals(1, highPriority.compareTo(actual))
        );
    }

    @Test
    void testEquals() {
        // when
        var actual = Data.BUILDER.build();

        // then
        assertAll(
                () -> assertTrue(actual.canEqual(Data.BUILDER.build())),
                () -> assertEquals(Data.BUILDER.build(), actual),
                () -> assertNotEquals(Price.builder().build(), actual)
        );
    }

    @Test
    void testHashCode() {
        // when
        var actual = Data.BUILDER.build().hashCode();

        // then
        assertAll(
                () -> assertEquals(Data.BUILDER.build().hashCode(), actual),
                () -> assertNotEquals(Price.builder().build().hashCode(), actual)
        );
    }

    @Test
    void testToString() {
        // when
        var actual = Data.BUILDER.build().toString();

        // then
        assertAll(
                () -> assertEquals(Data.BUILDER.build().toString(), actual),
                () -> assertNotEquals(Price.builder().build().toString(), actual)
        );
    }
}