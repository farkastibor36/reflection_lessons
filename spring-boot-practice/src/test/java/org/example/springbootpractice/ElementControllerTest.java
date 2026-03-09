package org.example.springbootpractice;

import org.example.springbootpractice.dto.ElementDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ElementControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getElementById() {
        webTestClient.get()
                .uri("/elements/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("Test Element")
                .jsonPath("$.quantity").isEqualTo(10)
                .jsonPath("$.price").isEqualTo(100)
                .jsonPath("$.description").isEqualTo("Test description");
    }

    @Test
    void getSaveElement() {
        webTestClient.post()
                .uri("/elements/save")
                .bodyValue(new ElementDto("Save test element", 1, 1000, "Save for test"))
                .exchange()
                .expectHeader().contentType("application/json")
                .expectStatus().isOk();
    }

    @Test
    void getAllElements() {
        webTestClient.get()
                .uri("/elements/all")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBodyList(ElementDto.class)
                .hasSize(1)
                .contains(new ElementDto("Test Element", 10, 100, "Test description"));
    }

    @Test
    void upgradeElement() {
        webTestClient.patch()
                .uri("/elements/1")
                .bodyValue(new ElementDto("Test element for patch", null, null, null))
                .exchange()
                .expectHeader().contentType("application/json")
                .expectStatus().isOk();
    }

    @Test
    void updateElement() {
        webTestClient.patch()
                .uri("/elements/1")
                .bodyValue(new ElementDto("Updated test element", 5, 150, "Updated description"))
                .exchange()
                .expectHeader().contentType("application/json")
                .expectStatus().isOk();
    }

    @Test
    void deleteElement() {
        webTestClient.delete()
                .uri("/elements/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}