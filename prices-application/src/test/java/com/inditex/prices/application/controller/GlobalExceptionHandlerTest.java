package com.inditex.prices.application.controller;

import com.inditex.prices.application.Data;
import com.inditex.prices.application.constant.Error;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleException() {
        // given
        var exception = new NoSuchElementException(Error.PRICE_NOT_FOUND);

        // when
        var actual = globalExceptionHandler.handleException(exception);

        // then
        assertEquals(Data.ERROR_RESPONSE(), actual);
    }
}