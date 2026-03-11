package org.example;

public class RequestCommandFactory {

    public static <T> Command<T> create(HttpMethod method, String url, String payload, Class<T> clazz) {
        return switch (method) {
            case GET -> new GetRequestCommand<>(url, clazz);
            case POST -> new PostRequestCommand<>(url, payload, clazz);
            case PUT -> new PutRequestCommand<>(url, payload, clazz);
            case PATCH -> new PatchRequestCommand<>(url, payload, clazz);
            case DELETE -> (Command<T>) new DeleteRequestCommand(url);
        };
    }

    public static <T> Command<T> create(HttpMethod method, String url, Class<T> clazz) {
        return create(method, url, null, clazz);
    }
}