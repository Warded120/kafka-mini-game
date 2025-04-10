package com.game.models;

import java.sql.Timestamp;

public record Message (
        long id,
        String username,
        String message,
        Timestamp timestamp
) {}
