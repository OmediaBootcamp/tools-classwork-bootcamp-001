package dev.omedia.creational.factory.method;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class SVReader {
    protected final Path path;

    protected SVReader(Path path) {
        this.path = path;
    }

    public abstract Iterable<String[]> lines() throws IOException;

    protected Stream<String> getLines() throws IOException {
        return Files.lines(path);
    }
}
