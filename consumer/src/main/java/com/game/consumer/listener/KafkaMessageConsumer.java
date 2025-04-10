package com.game.consumer.listener;

import com.game.models.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {
    @KafkaListener(topics = "message", groupId = "kafka-mini-game")
    public void consume(Message message) {
        System.out.println(message.username()
                + ": " + message.message()
                + "(" + message.timestamp() + ")");
    }
}
