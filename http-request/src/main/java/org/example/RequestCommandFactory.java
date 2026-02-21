package org.example;

public class RequestCommandFactory {
    public static <T> GetRequestCommand<T> createGet(String url, Class<T> clazz) {
        return new GetRequestCommand<>(url, clazz);
    }

    public static <T> PostRequestCommand<T> createPost(String url, String jsonPost, Class<T> clazz) {
        return new PostRequestCommand<>(url, jsonPost, clazz);
    }

    public static <T> PutRequestCommand<T> createPut(String url, String jsonPut, Class<T> clazz) {
        return new PutRequestCommand<>(url, jsonPut, clazz);
    }

    public static <T> PatchRequestCommand<T> createPatch(String url, String jsonPatch, Class<T> clazz) {
        return new PatchRequestCommand<>(url, jsonPatch, clazz);
    }

    public static DeleteRequestCommand createDelete(String url) {
        return new DeleteRequestCommand(url);
    }
}