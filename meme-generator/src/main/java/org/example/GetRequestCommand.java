package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GetRequestCommand implements Command<String> {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String getURL;

    @Override
    public String execute() throws IOException, InterruptedException {
        final String requestId = UUID.randomUUID().toString();
        log.info("requestId={} Sending get request to {}", requestId, getURL);

        final long startNs = System.nanoTime();
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getURL))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            final long durationMs = (System.nanoTime() - startNs) / 1_000_000;

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object json = objectMapper.readValue(response.body(), Object.class);
            String jsonFormat = objectMapper.writeValueAsString(json);

            logResponse(requestId, response, durationMs);
            return jsonFormat;
        } catch (IOException | InterruptedException e) {
            final long durationMs = (System.nanoTime() - startNs) / 1_000_000;
            log.error("requestId={} Error sending get request to {} (durationMs={}): {}", requestId, getURL, durationMs, e.getMessage());
            throw e;
        }
    }

    private void logResponse(String requestId, HttpResponse<String> response, long durationMs) {
        String contentType = response.headers().firstValue("Content-Type").orElse("<unknown>");
        int bodyLength = response.body() == null ? 0 : response.body().length();
        String bodyPreview = response.body() == null ? "" : response.body();
        log.info("requestId={} Status code: {} durationMs={} contentType={} bodyLength={}", requestId, response.statusCode(), durationMs, contentType, bodyLength);
        log.debug("requestId={} ResponseHeaders={}", requestId, response.headers().map());
        log.debug("requestId={} BodyPreview:\n{}", requestId, bodyPreview);
    }
}