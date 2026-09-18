package com.example.coreDemo.dto;

import java.time.Instant;

public record ConversationListDto(
        String name,
        Instant createdAt
) {
}
