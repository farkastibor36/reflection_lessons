package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PatchRequestCommandTest {
    private final Command<BlogPostResponse> patchRequestCommand = RequestCommandFactory.create(HttpMethod.PATCH, URLConstants.PATCH_POST_1, URLConstants.JSON_FOR_PATCH, BlogPostResponse.class);

    @Test
    void execute() throws IOException, InterruptedException {
        BlogPostResponse response = patchRequestCommand.execute();
        assertNotNull(response);
        assertEquals("updated title with patch", response.title());
        assertEquals("updated body with patch", response.body());
    }
}