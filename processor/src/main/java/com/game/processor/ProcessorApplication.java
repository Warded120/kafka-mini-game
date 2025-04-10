package com.game.processor;

import com.game.models.Message;
import com.game.models.RawMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Function;

@SpringBootApplication
public class ProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

	private static long idCounter = 0;

	@Bean
	public Function<RawMessage, Message> enhanceMessage() {
		return rawMessage -> {
			System.out.println("Enhancing Message: " + rawMessage);
			Message message = new Message(idCounter++, rawMessage.message(), Timestamp.valueOf(LocalDateTime.now()));
			System.out.println("Message: " + message);
			return message;
		};
	}

}
