package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestCommandTest {
    private static final String TITLE = "Test Title";
    private static final String BODY = "Test Body";
    private static final String USER_ID = "102";

    @Test
    void execute() throws IOException, InterruptedException {
        String jsonPost = """
                {
                "title":"%s",
                "body":"%s",
                "userId": "%s"
                }
                """.formatted(TITLE, BODY, USER_ID);
        PostRequestCommand postRequestCommand = new PostRequestCommand(URLConstants.POST, jsonPost, BlogPostResponse.class);
        BlogPostResponse blogPostResponse = (BlogPostResponse) postRequestCommand.execute();
        assertNotNull(blogPostResponse);
        assertEquals("Test Title", blogPostResponse.title());
        assertEquals("Test Body", blogPostResponse.body());
        assertEquals(102, blogPostResponse.userId());
    }
}