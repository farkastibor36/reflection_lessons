package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteRequestCommandTest {
    @Test
    public void execute() throws IOException, InterruptedException {
        Command<Integer> deleteRequestCommand = RequestCommandFactory.create(HttpMethod.DELETE, URLConstants.DELETE_POST_1, Integer.class);
        Integer status = deleteRequestCommand.execute();
        assertNotNull(status);
        assertTrue(Set.of(200, 204).contains(status), "Expected status code 200 or 204, but got: " + status);
    }
}