package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestCommandTest {
    private static final String USERNAME = System.getenv("IMGFLIP_USERNAME");
    private static final String PASSWORD = System.getenv("IMGFLIP_PASSWORD");

    @Test
    void shouldExecute() throws IOException, InterruptedException {
        String formData = "template_id=61532&username=" + USERNAME + "&password=" + PASSWORD + "&text0=Test&text1=Message";
        PostRequestCommand postRequestCommand = new PostRequestCommand(URLConstants.postURL, formData);
        String result = postRequestCommand.execute();
        assertNotNull(result);
        assertTrue(result.contains("\"success\" : true"));
        assertTrue(result.contains("\"url\" :"));
    }
}