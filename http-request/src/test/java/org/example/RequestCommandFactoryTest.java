package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestCommandFactoryTest {
    @Test
    void shouldCreateGetRequestCommand() {
        Command<BlogPostResponse> command =
                RequestCommandFactory.create(HttpMethod.GET, "/posts/1", BlogPostResponse.class);

        assertNotNull(command);
        assertInstanceOf(GetRequestCommand.class, command);
    }

    @Test
    void shouldCreatePostRequestCommand() {
        Command<BlogPostResponse> command =
                RequestCommandFactory.create(
                        HttpMethod.POST,
                        "/posts",
                        "{\"title\":\"test\"}",
                        BlogPostResponse.class
                );

        assertNotNull(command);
        assertInstanceOf(PostRequestCommand.class, command);
    }

    @Test
    void shouldCreatePutRequestCommand() {
        Command<BlogPostResponse> command =
                RequestCommandFactory.create(
                        HttpMethod.PUT,
                        "/posts/1",
                        "{\"title\":\"updated\"}",
                        BlogPostResponse.class
                );

        assertNotNull(command);
        assertInstanceOf(PutRequestCommand.class, command);
    }

    @Test
    void shouldCreatePatchRequestCommand() {
        Command<BlogPostResponse> command =
                RequestCommandFactory.create(
                        HttpMethod.PATCH,
                        "/posts/1",
                        "{\"title\":\"patched\"}",
                        BlogPostResponse.class
                );

        assertNotNull(command);
        assertInstanceOf(PatchRequestCommand.class, command);
    }

    @Test
    void shouldCreateDeleteRequestCommand() {
        Command<Void> command =
                RequestCommandFactory.create(HttpMethod.DELETE, "/posts/1", Void.class);

        assertNotNull(command);
        assertInstanceOf(DeleteRequestCommand.class, command);
    }

    @Test
    void shouldCreateGetRequestCommandUsingOverloadedMethod() {
        Command<BlogPostResponse> command =
                RequestCommandFactory.create(HttpMethod.GET, "/posts/1", BlogPostResponse.class);

        assertNotNull(command);
        assertInstanceOf(GetRequestCommand.class, command);
    }
}
