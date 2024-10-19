package com.inditex.prices.infrastructure.entity;

import com.inditex.prices.infrastructure.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandEntityTest {

    @Test
    void testAllArgsConstructor() {
        // when
        var actual = new BrandEntity(1, "ZARA");

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND_ID, actual.getId()),
                () -> assertEquals(Data.BRAND.getName(), actual.getName())
        );
    }

    @Test
    void testSetters() {
        // given
        var actual = new BrandEntity();

        // when
        actual.setId(1);
        actual.setName("ZARA");

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.BRAND_ID, actual.getId()),
                () -> assertEquals(Data.BRAND.getName(), actual.getName())
        );
    }

}