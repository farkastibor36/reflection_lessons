package org.example;

public class RequestCommandFactory {

    public static <T> Command<T> create(HttpMethod method, String url, String jsonPost, Class<T> clazz) {
        return switch (method) {
            case GET -> new GetRequestCommand<>(url, clazz);
            case POST -> new PostRequestCommand<>(url, jsonPost, clazz);
            case PUT -> new PutRequestCommand<>(url, jsonPost, clazz);
            case PATCH -> new PatchRequestCommand<>(url, jsonPost, clazz);
            case DELETE -> null;
        };
    }

    public static DeleteRequestCommand createDelete(String url) {
        return new DeleteRequestCommand(url);
    }

    public static <T> Command<T> create(HttpMethod method, String url, Class<T> clazz) {
        return create(method, url, null, clazz);
    }
}