package org.example;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class PatchRequestCommand<T> implements Command<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Logger logger = LoggerFactory.getLogger(PatchRequestCommand.class);
    private final String url;
    private final String jsonPatch;
    private final Class<T> responseType;

    @Override
    public T execute() throws IOException, InterruptedException {
        HttpRequest patchRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json, charset=UTF-8")
                .header("Accept", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonPatch))
                .build();
        HttpResponse<String> response = client.send(patchRequest, HttpResponse.BodyHandlers.ofString());
        logResponse(response);
        return mapper.readValue(response.body(), responseType);
    }

    private void logResponse(HttpResponse<String> response) {
        logger.info("Status code: " + response.statusCode());
        logger.debug("Body: " + response.body());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}