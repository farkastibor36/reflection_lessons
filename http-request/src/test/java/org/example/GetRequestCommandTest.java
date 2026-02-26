package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetRequestCommandTest {

    @Test
    void execute() throws IOException, InterruptedException {
        GetRequestCommand getRequestCommand = new GetRequestCommand(URLConstants.GET_POSTS, BlogPostResponse[].class);
        BlogPostResponse[] blog = (BlogPostResponse[]) getRequestCommand.execute();
        assertNotNull(blog);
        assertTrue(blog.length > 0);
    }
}