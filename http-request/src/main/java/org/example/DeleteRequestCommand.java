package org.example;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class DeleteRequestCommand implements Command<Void> {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Logger logger = LoggerFactory.getLogger(DeleteRequestCommand.class);
    private final String url;

    @Override
    public Void execute() throws IOException, InterruptedException {
        String jsonBody = "{\"id\":1}";
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json, charset=UTF-8")
                .header("Accept", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        logResponse(response);
        return null;
    }

    private void logResponse(HttpResponse<String> response) {
        logger.info("Status code: " + response.statusCode());
        logger.debug("Body: " + response.body());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        System.out.println("Delete request sent.");
    }
}