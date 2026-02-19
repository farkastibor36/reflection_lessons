package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetRequestCommandTest {

    @Test
    void shouldExecute() throws IOException, InterruptedException {
        GetRequestCommand getRequestCommand = new GetRequestCommand(URLConstants.getURL);

        String result = getRequestCommand.execute();
        assertNotNull(result);
        assertTrue(result.contains("\"success\" : true"));
    }
}