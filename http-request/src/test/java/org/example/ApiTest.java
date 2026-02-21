package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ApiTest {
    private WebTestClient webTestClient;

    private static final String USERNAME = System.getenv("IMGFLIP_USERNAME");
    private static final String PASSWORD = System.getenv("IMGFLIP_PASSWORD");

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("https://api.imgflip.com")
                .build();
    }

    @Test
    void shouldExecuteGetRequest() {
        webTestClient.get()
                .uri("/get_memes")
                .exchange().expectStatus().isOk()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.data.memes").isArray()
                .jsonPath("$.data.memes[0].id").exists();
    }

    @Test
    void shouldExecutePostRequest() {
        String formData = "template_id=61532&username=" + USERNAME + "&password=" + PASSWORD + "&text0=Test&text1=Message";
        webTestClient.post()
                .uri("/caption_image")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.data.url").exists();
    }
}
