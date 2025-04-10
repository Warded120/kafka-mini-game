
RawMessage {
    message: "string"
}

Message {
    id: 0
    message: "string"
    timestamp: "date" 
}

all kafka topics must have group:
- group: kafka-mini-game

- endpoint: /send
- producer: consume http request, send to "raw-message"
- processor; consume from raw-message, set id and timestamp, send to "message"
- consumer: consume from "message"
