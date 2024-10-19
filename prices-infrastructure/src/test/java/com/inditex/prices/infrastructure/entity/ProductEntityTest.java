package com.inditex.prices.infrastructure.entity;

import com.inditex.prices.infrastructure.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductEntityTest {

    @Test
    void testAllArgsConstructor() {
        // when
        var actual = new ProductEntity(35455, "Product A");

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.PRODUCT_ID, actual.getId()),
                () -> assertEquals(Data.PRODUCT.getName(), actual.getName())
        );
    }

    @Test
    void testSetters() {
        // given
        var actual = new ProductEntity();

        // when
        actual.setId(35455);
        actual.setName("Product A");

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Data.PRODUCT_ID, actual.getId()),
                () -> assertEquals(Data.PRODUCT.getName(), actual.getName())
        );
    }
}