package com.inditex.prices.application.controller;

import com.inditex.prices.application.constant.Error;
import com.inditex.prices.application.mapper.PriceResponseMapper;
import com.inditex.prices.domain.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.inditex.prices.application.Data.BRAND_ID;
import static com.inditex.prices.application.Data.BUILDER;
import static com.inditex.prices.application.Data.PRODUCT_ID;
import static com.inditex.prices.application.Data.RESPONSE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

    @InjectMocks
    private PricesController pricesController;

    @Mock
    private PriceService priceService;

    @Mock
    private PriceResponseMapper priceResponseMapper;

    @Test
    void getPriceNoPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceService.findApplicablePrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(Optional.empty());

        // when
        var actual = assertThrows(
                NoSuchElementException.class,
                () -> pricesController.getPrice(date, PRODUCT_ID, BRAND_ID)
        );

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Error.PRICE_NOT_FOUND, actual.getMessage())
        );

        verify(priceService, times(1)).findApplicablePrice(BRAND_ID, PRODUCT_ID, date);
        verifyNoMoreInteractions(priceService, priceResponseMapper);
    }

    @Test
    void getPriceOnePrice() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);
        var price = BUILDER.build();
        var expected = ResponseEntity.of(Optional.of(RESPONSE()));

        when(priceService.findApplicablePrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(Optional.of(price));
        when(priceResponseMapper.toResponse(price)).thenReturn(RESPONSE());

        // when
        var actual = pricesController.getPrice(date, PRODUCT_ID, BRAND_ID);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(priceService, times(1)).findApplicablePrice(BRAND_ID, PRODUCT_ID, date);
        verify(priceResponseMapper, times(1)).toResponse(price);
        verifyNoMoreInteractions(priceService, priceResponseMapper);
    }

    @Test
    void getRequest() {
        // when
        var actual = pricesController.getRequest();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty())
        );
    }

    @Test
    void _getPrice() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceService.findApplicablePrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(Optional.empty());

        // when
        var actual = assertThrows(
                NoSuchElementException.class,
                () -> pricesController._getPrice(date, PRODUCT_ID, BRAND_ID)
        );

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(Error.PRICE_NOT_FOUND, actual.getMessage())
        );

        verify(priceService, times(1)).findApplicablePrice(BRAND_ID, PRODUCT_ID, date);
        verifyNoMoreInteractions(priceService, priceResponseMapper);
    }
}