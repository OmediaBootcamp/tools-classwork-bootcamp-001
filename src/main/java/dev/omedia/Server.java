package dev.omedia;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    public void start() throws IOException {
        try (
                ServerSocket serverSocket = new ServerSocket(Config.port);
                Socket client = serverSocket.accept()
        ) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                System.out.println(reader.readLine());
            }
        }
    }
}
