// src/test/java/com/ch5/ch5techno/order/OrderControllerIT.java
package com.ch5.ch5techno.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OrderControllerIT {
    @Autowired TestRestTemplate rest;

    @Test void list_returns_200() {
        var resp = rest.getForEntity("/api/orders", String.class);
        assertThat(resp.getStatusCode().value()).isEqualTo(200);
    }
}
