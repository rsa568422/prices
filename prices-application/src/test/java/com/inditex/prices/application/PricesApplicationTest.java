package com.inditex.prices.application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PricesApplicationTest {

    @Test
    void main() {
        // given
        var args = new String[0];

        try (var mockStatic = Mockito.mockStatic(SpringApplication.class)) {

            // when
            assertDoesNotThrow(() -> PricesApplication.main(args));

            // then
            mockStatic.verify(() -> SpringApplication.run(PricesApplication.class, args));
        }
    }
}