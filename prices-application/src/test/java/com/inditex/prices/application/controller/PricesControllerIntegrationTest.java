package com.inditex.prices.application.controller;

import com.inditex.prices.application.controller.model.ErrorResponse;
import com.inditex.prices.application.controller.model.PriceResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.inditex.prices.application.Data.BRAND_ID;
import static com.inditex.prices.application.Data.CURRENCY;
import static com.inditex.prices.application.Data.PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(properties = {
        "SPRING_DATASOURCE_URL=jdbc:h2:mem:prices",
        "SPRING_DATASOURCE_USERNAME=sa",
        "SPRING_DATASOURCE_PASSWORD=",
        "SPRING_DATASOURCE_DRIVER=org.h2.Driver",
        "SPRING_JPA_DIALECT=org.hibernate.dialect.H2Dialect"
})
class PricesControllerIntegrationTest {

    @Autowired
    private TestRestTemplate client;

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T08:00:00.000Z, 1, 35.5",
            "2020-06-14T14:00:00.000Z, 2, 25.45",
            "2020-06-14T19:00:00.000Z, 1, 35.5",
            "2020-06-15T08:00:00.000Z, 3, 30.5",
            "2020-06-16T19:00:00.000Z, 4, 38.95"
    })
    void getPrice(OffsetDateTime date, Integer priceList, Double price) {
        // when
        var response = client.getForEntity(createUri(date.toString()), PriceResponse.class);

        // then
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> {
                    assertNotNull(response.getBody());
                    var body = response.getBody();
                    assertAll(
                            () -> assertEquals(PRODUCT_ID, body.getProductId()),
                            () -> assertEquals(BRAND_ID, body.getBrandId()),
                            () -> assertEquals(priceList, body.getPriceList()),
                            () -> assertEquals(price, body.getPrice()),
                            () -> assertEquals(CURRENCY, body.getCurrency())
                    );
                }
        );
    }

    @Test
    void noPrices() {
        // given
        var date = OffsetDateTime.of(LocalDate.of(2024, Month.OCTOBER, 19), LocalTime.MIN, ZoneOffset.UTC);

        // when
        var response = client.getForEntity(createUri(date.toString()), ErrorResponse.class);

        // then
        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()),
                () -> assertEquals(APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> {
                    assertNotNull(response.getBody());
                    var body = response.getBody();
                    assertAll(
                            () -> assertEquals("no such element", body.getType()),
                            () -> assertEquals(404, body.getStatus()),
                            () -> assertEquals("No Such Element Exception", body.getTitle()),
                            () -> assertEquals("No se ha encontrado ningún precio aplicable", body.getDetail()),
                            () -> assertEquals("/prices", body.getInstance())
                    );
                }
        );
    }

    @Test
    void badDate() {
        // when
        var response = client.getForEntity(createUri("2020-06-14Z"), ErrorResponse.class);

        // then
        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(APPLICATION_PROBLEM_JSON, response.getHeaders().getContentType()),
                () -> {
                    assertNotNull(response.getBody());
                    var body = response.getBody();
                    assertAll(
                            () -> assertEquals("about:blank", body.getType()),
                            () -> assertEquals(400, body.getStatus()),
                            () -> assertEquals("Bad Request", body.getTitle()),
                            () -> assertEquals("Failed to convert 'date' with value: '2020-06-14Z'", body.getDetail()),
                            () -> assertEquals("/prices", body.getInstance())
                    );
                }
        );
    }

    private String createUri(String date) {
        return String.format("http://localhost:%d/prices?date=%s&productId=35455&brandId=1", port, date);
    }
}