package com.inditex.pricing.integration;

import com.inditex.pricing.PricingApplication;
import com.inditex.pricing.model.PriceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = PricingApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.profiles.active=integration"
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)

public class PricingIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "/api/prices";

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test1_time10_day14() {
        String url = BASE_URL + "?brandId=1&productId=35455&date=2020-06-14T10:00:00";
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getPrice()).isEqualTo(35.50);
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test2_time16_day14() {
        String url = BASE_URL + "?brandId=1&productId=35455&date=2020-06-14T16:00:00";
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getPrice()).isEqualTo(25.45);
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test3_time21_day14() {
        String url = BASE_URL + "?brandId=1&productId=35455&date=2020-06-14T21:00:00";
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getPrice()).isEqualTo(35.50);
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void test4_time10_day15() {
        String url = BASE_URL + "?brandId=1&productId=35455&date=2020-06-15T10:00:00";
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getPrice()).isEqualTo(30.50);
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void test5_time21_day16() {
        String url = BASE_URL + "?brandId=1&productId=35455&date=2020-06-16T21:00:00";
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getPrice()).isEqualTo(38.95);
    }

    @Test
    @DisplayName("Test 6: petición con 404 por no encontrar precio")
    void test6_noPriceFound() {
        String url = BASE_URL + "?brandId=999&productId=999999&date=2020-01-01T00:00:00";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("{\"timestamp\":null,\"status\":404,\"message\":\"No price found for brandId=999, productId=999999 at date=2020-01-01T00:00\"}");
    }


}