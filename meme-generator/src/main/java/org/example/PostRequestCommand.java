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
public class PostRequestCommand implements Command<String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    private final String postURL;
    private final String formData;

    @Override
    public String execute() throws IOException, InterruptedException {
        final String requestId = UUID.randomUUID().toString();
        int requestBodyLength = formData == null ? 0 : formData.length();
        log.info("requestId={} Sending post request to {} (requestBodyLength={})", requestId, postURL, requestBodyLength);

        final long startMs = System.nanoTime();
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(postURL))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .build();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            final long durationMs = (System.nanoTime() - startMs) / 1_000_000;

            Object jsonFormat2 = objectMapper.readValue(postResponse.body(), Object.class);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonPostFormat = objectMapper.writeValueAsString(jsonFormat2);

            logResponse(requestId, postResponse, jsonPostFormat, durationMs);
            return jsonPostFormat;
        } catch (IOException | InterruptedException e) {
            final long durationMs = (System.nanoTime() - startMs) / 1_000_000;
            log.error("requestId={} Error sending post request to {} (durationMs={}): {}", requestId, postURL, durationMs, e.getMessage());
            throw e;
        }
    }

    private void logResponse(String requestId, HttpResponse<String> postResponse, String jsonPostFormat, long durationMs) {
        String contentType = postResponse.headers().firstValue("Content-Type").orElse("<unknown>");
        int bodyLength = postResponse.body() == null ? 0 : postResponse.body().length();
        String bodyPreview = jsonPostFormat == null ? "" : jsonPostFormat;
        log.info("requestId={} Status code: {} durationMs={} contentType={} bodyLength={}", requestId, postResponse.statusCode(), durationMs, contentType, bodyLength);
        log.debug("requestId={} ResponseHeaders={}", requestId, postResponse.headers().map());
        log.debug("requestId={} BodyPreview:\n{}", requestId, bodyPreview);
    }
}