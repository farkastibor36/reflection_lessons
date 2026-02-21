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
public class PutRequestCommand<T> implements Command<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Logger logger = LoggerFactory.getLogger(PutRequestCommand.class);
    private final String url;
    private final String jsonPut;
    private final Class<T> responseType;

    @Override
    public T execute() throws IOException, InterruptedException {
        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json, charset=UTF-8")
                .header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonPut))
                .build();

        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        logResponse(putResponse);
        return mapper.readValue(putResponse.body(), responseType);
    }

    private void logResponse(HttpResponse<String> putResponse) {
        logger.info("Status code: " + putResponse.statusCode());
        logger.debug("Body: " + putResponse.body());
        System.out.println("Status code: " + putResponse.statusCode());
        System.out.println("Response body: " + putResponse.body());
    }
}