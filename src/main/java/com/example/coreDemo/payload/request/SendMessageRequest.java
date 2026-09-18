package com.example.coreDemo.payload.request;

import lombok.Getter;

@Getter
public class SendMessageRequest {
    private String conversationId;
    private String content;
}
