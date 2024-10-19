package com.inditex.prices.domain.service;

import com.inditex.prices.domain.Data;
import com.inditex.prices.domain.repository.PriceRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private TestPriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    void findApplicablePriceNoPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceRepository.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date)).thenReturn(List.of());

        // when
        var actual = priceService.findApplicablePrice(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty())
        );

        verify(priceRepository, times(1))
                .findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verifyNoMoreInteractions(priceRepository);
    }

    @Test
    void findApplicablePriceOnePrice() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);
        var expected = Data.BUILDER.build();

        when(priceRepository.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date)).thenReturn(List.of(expected));

        // when
        var actual = priceService.findApplicablePrice(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> {
                    assertTrue(actual.isPresent());
                    var price = actual.get();
                    assertEquals(expected, price);
                }
        );

        verify(priceRepository, times(1))
                .findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verifyNoMoreInteractions(priceRepository);
    }

    @Test
    void findApplicablePriceTwoPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.MIN, ZoneOffset.UTC);

        when(priceRepository.findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date))
                .thenReturn(List.of(Data.BUILDER.build(), Data.SECOND_PRICE));

        // when
        var actual = priceService.findApplicablePrice(Data.BRAND_ID, Data.PRODUCT_ID, date);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> {
                    assertTrue(actual.isPresent());
                    var price = actual.get();
                    assertEquals(Data.SECOND_PRICE, price);
                }
        );

        verify(priceRepository, times(1))
                .findApplicablePrices(Data.BRAND_ID, Data.PRODUCT_ID, date);
        verifyNoMoreInteractions(priceRepository);
    }

    private static final class TestPriceService extends PriceService {
        public TestPriceService(PriceRepository priceRepository) {
            super(priceRepository);
        }
    }
}