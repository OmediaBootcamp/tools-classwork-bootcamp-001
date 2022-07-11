package dev.omedia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Config {
    private final String fileName;
    private final int size;
    private static final String FILE_PATH = "config.txt";
    private static final String DEFAULT_PATH = "./tmp/a.txt";
    private static final String DEFAULT_SIZE = "500";
    private static final Config INSTANCE = new Config();

    private Config() {
        String[] lines = getConfigLine();

        this.fileName = lines[1];
        this.size = Integer.parseInt(lines[0]);
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getSize() {
        return this.size;
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    private String[] getConfigLine() {
        String[] defaultArray = {DEFAULT_SIZE, DEFAULT_PATH};
        try {
            return Files.lines(Path.of(FILE_PATH))
                    .map(line -> line.split(" "))
                    .findFirst()
                    .orElse(defaultArray);
        } catch (IOException e) {
            e.printStackTrace();
            return defaultArray;
        }
    }

}