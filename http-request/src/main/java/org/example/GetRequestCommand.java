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
public class GetRequestCommand<T> implements Command<T> {
    private static ObjectMapper mapper = new ObjectMapper();
    private static HttpClient client = HttpClient.newHttpClient();
    private static Logger logger = LoggerFactory.getLogger(GetRequestCommand.class);
    private final String url;
    private final Class<T> responseType;

    @Override
    public T execute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
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