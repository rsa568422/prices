package com.inditex.prices.infrastructure.repository;

public final class PriceQuery {

    private PriceQuery() { }

    public static final String FIND_BY_BRAND_ID_AND_PRODUCT_ID_AND_DATE_QUERY = """
            select p
            from PriceEntity p
            where p.brand.id = ?1
            and p.product.id = ?2
            and ?3 between p.startDate and p.endDate
        """;
}
