package com.example.coreDemo.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    private String content;
    private String sendTime;
    private String messageId;
    private String conversationId;
}
