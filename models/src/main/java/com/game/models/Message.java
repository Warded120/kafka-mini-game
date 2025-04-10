package com.game.models;

import java.sql.Timestamp;

public record Message (
        long id,
        String message,
        Timestamp timestamp
) {}
