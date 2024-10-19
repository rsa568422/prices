package com.inditex.prices.infrastructure.adapter;

import com.inditex.prices.infrastructure.Data;
import com.inditex.prices.infrastructure.mapper.PriceMapper;
import com.inditex.prices.infrastructure.repository.PriceJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PriceMapper priceMapper;

    @Test
    void findApplicablePricesNoPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceJpaRepository.findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date))
                .thenReturn(List.of());

        // when
        var actual = priceRepositoryAdapter.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty())
        );

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verifyNoMoreInteractions(priceJpaRepository, priceMapper);
    }

    @Test
    void findApplicablePricesOnePrice() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceJpaRepository.findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date))
                .thenReturn(List.of(Data.FIRST_PRICE_ENTITY));
        when(priceMapper.toModel(Data.FIRST_PRICE_ENTITY)).thenReturn(Data.FIRST_PRICE);

        // when
        var actual = priceRepositoryAdapter.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()),
                () -> assertEquals(1, actual.size()),
                () -> assertEquals(Data.FIRST_PRICE, actual.get(0))
        );

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verify(priceMapper, times(1)).toModel(Data.FIRST_PRICE_ENTITY);
        verifyNoMoreInteractions(priceJpaRepository, priceMapper);
    }

    @Test
    void findApplicablePricesTwoPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceJpaRepository.findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date))
                .thenReturn(List.of(Data.FIRST_PRICE_ENTITY, Data.SECOND_PRICE_ENTITY));
        when(priceMapper.toModel(Data.FIRST_PRICE_ENTITY)).thenReturn(Data.FIRST_PRICE);
        when(priceMapper.toModel(Data.SECOND_PRICE_ENTITY)).thenReturn(Data.SECOND_PRICE);

        // when
        var actual = priceRepositoryAdapter.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()),
                () -> assertEquals(2, actual.size()),
                () -> assertEquals(Data.FIRST_PRICE, actual.get(0)),
                () -> assertEquals(Data.SECOND_PRICE, actual.get(1))
        );

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndDate(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verify(priceMapper, times(1)).toModel(Data.FIRST_PRICE_ENTITY);
        verify(priceMapper, times(1)).toModel(Data.SECOND_PRICE_ENTITY);
        verifyNoMoreInteractions(priceJpaRepository, priceMapper);
    }
}