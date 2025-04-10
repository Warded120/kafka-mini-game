package com.game.controller;

import com.game.controller.model.RawMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class ControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);
	}

	private final StreamBridge streamBridge;

	private static final String RAW_MESSAGE_TOPIC = "raw-message";

	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestBody RawMessage rawMessage) {
		boolean isSent = streamBridge.send(RAW_MESSAGE_TOPIC, rawMessage);
		return isSent
				? ResponseEntity.ok("Message sent: " + rawMessage.message())
				: ResponseEntity.notFound().build();
	}

}
