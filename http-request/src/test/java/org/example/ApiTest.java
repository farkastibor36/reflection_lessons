package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ApiTest {
    private WebTestClient webTestClient;
    private WebTestClient jsonPlaceholderClient;

    private static final String USERNAME = System.getenv("IMGFLIP_USERNAME");
    private static final String PASSWORD = System.getenv("IMGFLIP_PASSWORD");

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("https://api.imgflip.com")
                .build();

        jsonPlaceholderClient = WebTestClient
                .bindToServer()
                .baseUrl("https://jsonplaceholder.typicode.com")
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

    @Test
    void shouldExecutePutRequest(){
        String jsonForPut = URLConstants.JSON_FOR_PUT;
        jsonPlaceholderClient.put()
                .uri("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonForPut)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("updated title")
                .jsonPath("$.body").isEqualTo("updated body");
    }

    @Test
    void shouldExecutePatchRequest(){
        String jsonForPatch = URLConstants.JSON_FOR_PATCH;

        jsonPlaceholderClient.patch()
                .uri("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(jsonForPatch)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("updated title with patch")
                .jsonPath("$.body").isEqualTo("updated body with patch");
    }

    @Test
    void shouldExecuteDeleteRequest(){
        jsonPlaceholderClient.delete()
                .uri("/posts/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{}");
    }
}