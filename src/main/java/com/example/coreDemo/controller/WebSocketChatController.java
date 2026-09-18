package com.example.coreDemo.controller;

import com.example.coreDemo.payload.request.SendMessageRequest;
import com.example.coreDemo.payload.response.MessageResponse;
import com.example.coreDemo.service.ConversationService;
import com.example.coreDemo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send.message")
    public void sendMessage(
            SendMessageRequest request,
            Principal principal
    ) {

        MessageResponse response =
                messageService.sendMessage(
                        request,
                        principal
                );

        messagingTemplate.convertAndSend(
                "/topic/chat/" + request.getConversationId(),
                response
        );
    }
}
