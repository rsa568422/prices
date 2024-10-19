package com.inditex.prices.application.controller;

import com.inditex.prices.application.constant.Error;
import com.inditex.prices.application.controller.api.PricesApi;
import com.inditex.prices.application.controller.model.PriceResponse;
import com.inditex.prices.application.mapper.PriceResponseMapper;
import com.inditex.prices.domain.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class PricesController implements PricesApi {

    private final PriceService priceService;

    private final PriceResponseMapper priceResponseMapper;

    @Override
    public ResponseEntity<PriceResponse> getPrice(OffsetDateTime date, Integer productId, Integer brandId) {
        return priceService.findApplicablePrice(brandId, productId, date)
                .map(priceResponseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException(Error.PRICE_NOT_FOUND));
    }
}
