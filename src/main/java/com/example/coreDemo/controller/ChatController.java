package com.example.coreDemo.controller;

import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.MessageResponse;
import com.example.coreDemo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @GetMapping("/{chatId}/messages")
    public ApiResponse<List<MessageResponse>> getMessages(
            @PathVariable String chatId
    ) {

        return ApiResponse.<List<MessageResponse>>builder()
                .status("OK")
                .data(messageService.getConversationMessages(chatId))
                .build();
    }

}
