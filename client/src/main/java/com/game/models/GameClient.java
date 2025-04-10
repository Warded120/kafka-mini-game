package com.game.models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class GameClient {
  private static final HttpClient client = HttpClient.newBuilder()
      .version(Version.HTTP_1_1)
      .connectTimeout(Duration.ofSeconds(5))
      .build();

  public static void main(String[] args) {
    final var sendRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/send"))
        .POST(BodyPublishers.ofString("{ \"message\": \"hello world!\" }"))
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
