package com.inditex.prices.application.mapper;

import com.inditex.prices.application.controller.model.PriceResponse;
import com.inditex.prices.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(target = "price", source = "amount")
    PriceResponse toResponse(Price model);
}
