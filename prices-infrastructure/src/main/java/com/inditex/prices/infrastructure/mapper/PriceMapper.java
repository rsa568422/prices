package com.inditex.prices.infrastructure.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "amount", source = "price")
    Price toModel(PriceEntity price);
}
