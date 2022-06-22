package dev.omedia.creational.factory.method;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class TSVReader extends SVReader{
    public TSVReader(Path path) {
        super(path);
    }

    @Override
    public Iterable<String[]> lines() throws IOException {
        return getLines()
                .map(line->line.split("\\t"))
                .collect(Collectors.toList());
    }
}
