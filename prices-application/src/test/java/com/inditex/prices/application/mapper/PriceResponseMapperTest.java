package com.inditex.prices.application.mapper;

import com.inditex.prices.application.configuration.MapperTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static com.inditex.prices.application.Data.AMOUNT;
import static com.inditex.prices.application.Data.BRAND_ID;
import static com.inditex.prices.application.Data.CURRENCY;
import static com.inditex.prices.application.Data.END_DATE;
import static com.inditex.prices.application.Data.BUILDER;
import static com.inditex.prices.application.Data.PRICE_LIST;
import static com.inditex.prices.application.Data.PRODUCT_ID;
import static com.inditex.prices.application.Data.RESPONSE;
import static com.inditex.prices.application.Data.START_DATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ContextConfiguration(classes = MapperTestConfig.class)
class PriceResponseMapperTest {

    @Autowired
    private PriceResponseMapper priceResponseMapper;

    @Test
    void toResponse() {
        // given
        var price = BUILDER.build();
        var expected = RESPONSE();

        // when
        var actual = priceResponseMapper.toResponse(price);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void toResponseNull() {
        assertNull(priceResponseMapper.toResponse(null));
    }

    @Test
    void toModelBrandNull() {
        // given
        var price = BUILDER.build();
        price.setBrandId(null);

        // when
        var actual = priceResponseMapper.toResponse(price);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertNull(actual.getBrandId()),
                () -> assertEquals(PRODUCT_ID, actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(AMOUNT.doubleValue(), actual.getPrice()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }

    @Test
    void toModelProductNull() {
        // given
        var price = BUILDER.build();
        price.setProductId(null);

        // when
        var actual = priceResponseMapper.toResponse(price);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(BRAND_ID, actual.getBrandId()),
                () -> assertNull(actual.getProductId()),
                () -> assertEquals(PRICE_LIST, actual.getPriceList()),
                () -> assertEquals(START_DATE, actual.getStartDate()),
                () -> assertEquals(END_DATE, actual.getEndDate()),
                () -> assertEquals(AMOUNT.doubleValue(), actual.getPrice()),
                () -> assertEquals(CURRENCY, actual.getCurrency())
        );
    }
}