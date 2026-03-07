package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PutRequestCommandTest {
    Command<BlogPostResponse> putRequestCommand = RequestCommandFactory.create(HttpMethod.PUT, URLConstants.PUT_POST_1, URLConstants.JSON_FOR_PUT, BlogPostResponse.class);

    @Test
    public void execute() throws InterruptedException, IOException {

        BlogPostResponse response = putRequestCommand.execute();

        assertNotNull(response);

        assertEquals(1, response.id());
        assertEquals(1, response.userId());
        assertEquals("updated title", response.title());
        assertEquals("updated body", response.body());
    }
}