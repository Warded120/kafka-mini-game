package com.game.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

@Component
public class ClientRunner implements CommandLineRunner {

    private static String name;
    private static final String jsonMessage =
            """
            {
                "username": "%s",
                "message": "%s"
            }
            """;

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("enter your name: ");
            name = scanner.nextLine();

            while (true) {
                Thread.sleep(1500);
                System.out.print("enter your message: ");
                String message = scanner.nextLine();
                if (message.equals("exit")) {
                    System.exit(0);
                }
                sendRequest(message);
            }
        }
    }

    private static void sendRequest(String message) {
        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        final var sendRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/send"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format(jsonMessage, name, message)))
                .build();

        try {
            client.send(sendRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            System.out.println("something has gone wrong during request!");
            exception.printStackTrace();
        }
    }
}
