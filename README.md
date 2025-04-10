
Message {
    message: ""
}

Message {
    id: 0
    message: ""
    timestamp: "" 
}

endpoint: /send
producer: consume http request, send to "raw-message"
processor; consume from raw-message, set id and timestamp, send to "message"
consumer: consume from "message"
