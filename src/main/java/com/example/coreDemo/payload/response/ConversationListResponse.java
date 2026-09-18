package com.example.coreDemo.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class ConversationListResponse {
    private String name;
    private String createdAt;
}
