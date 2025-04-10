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
		return rawMessage ->
			new Message(
				idCounter++,
				rawMessage.username(),
				rawMessage.message(),
				Timestamp.valueOf(LocalDateTime.now())
			);
	}
}
