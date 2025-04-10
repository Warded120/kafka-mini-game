package com.game.consumer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

  private static String name;
  private static final String jsonMessage = """
      {
          "username": "%s",
          "message": "%s"
      }
      """;

  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner() {
    return args -> {
      try (final var sc = new Scanner(System.in)) {
        System.out.print("enter your name: ");
        name = sc.nextLine();
        while (true) {
          final var message = sc.nextLine();
          if (message.equals("exit")) {
            System.exit(0);
          }
          sendRequest(message);
        }
      }
    };
  }

  private static void sendRequest(String message) {
    final HttpClient client = HttpClient.newBuilder()
        .version(Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    final var sendRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/send"))
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofString(String.format(jsonMessage, name, message)))
        .build();

    try {
      client.send(sendRequest, BodyHandlers.ofString());
      System.out.println("Sent message");
    } catch (IOException | InterruptedException exception) {
      System.out.println("something has gone wrong during request!");
      exception.printStackTrace();
    }
  }
}
