package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteRequestCommandTest {
    @Test
    public void execute() throws IOException, InterruptedException {
        DeleteRequestCommand deleteRequestCommand = RequestCommandFactory.createDelete(URLConstants.DELETE_POST_1);
        deleteRequestCommand.execute();
        assertNotNull(deleteRequestCommand.execute());
        assertTrue(Set.of(200, 204).contains(deleteRequestCommand.execute()), "Expected status code 200 or 204, but got: " + deleteRequestCommand.execute());
    }
}