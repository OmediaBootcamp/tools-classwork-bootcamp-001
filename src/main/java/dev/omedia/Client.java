package dev.omedia;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    public void start() throws IOException {
        try (Socket socket = new Socket(Config.host, Config.port)) {
            String name = "java";

            try (OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(name.getBytes());
            }
        }
    }
}
