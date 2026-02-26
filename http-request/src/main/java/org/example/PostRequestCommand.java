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
public class PostRequestCommand<T> implements Command<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Logger logger = LoggerFactory.getLogger(PostRequestCommand.class);
    private final String url;
    private final String jsonPost;
    private final Class<T> responseType;

    @Override
    public T execute() throws IOException, InterruptedException {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPost))
                .build();

        HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
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