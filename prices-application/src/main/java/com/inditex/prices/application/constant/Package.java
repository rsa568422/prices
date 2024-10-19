package com.inditex.prices.application.constant;

public final class Package {

    private Package() { }

    private static final String INFRASTRUCTURE = ".infrastructure";

    private static final String ENTITY = ".entity";

    private static final String REPOSITORY = ".repository";

    public static final String PRICES_PACKAGE = "com.inditex.prices";

    public static final String ENTITY_PACKAGE = PRICES_PACKAGE + INFRASTRUCTURE + ENTITY;

    public static final String REPOSITORY_PACKAGE = PRICES_PACKAGE + INFRASTRUCTURE + REPOSITORY;
}
