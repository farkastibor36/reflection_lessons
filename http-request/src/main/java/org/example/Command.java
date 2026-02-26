package org.example;

import java.io.IOException;

public interface Command<T> {
    T execute() throws IOException, InterruptedException;
}