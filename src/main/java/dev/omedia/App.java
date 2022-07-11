package dev.omedia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        createFile();
    }

    public static void createFile() {
        byte[] byteArr = new byte[Config.getInstance().getSize()];

        Arrays.fill(byteArr, (byte) 65);
        try {
            Files.write(Path.of(Config.getInstance().getFileName())
                    , byteArr, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
