package org.example;

public record BlogPostResponse(Integer userId, Integer id, String title, String body) {
}