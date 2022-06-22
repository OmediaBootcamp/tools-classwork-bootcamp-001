package dev.omedia.creational.factory.method;

import dev.omedia.creational.factory.method.exception.FactoryMethodException;
import java.nio.file.Path;

public final class SVReaderFactory {
    public static SVReader create(String path) throws FactoryMethodException {
        Path filePath = Path.of(path);

        if (path.endsWith("csv")) {
            return new CSVReader(filePath);
        }

        if (path.endsWith("tsv")) {
            return new TSVReader(filePath);
        }

        throw new FactoryMethodException(String.format("Invalid path: %s. It should be end with csv or tsv.", path));
    }
}
