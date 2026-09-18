package com.example.coreDemo.controller;

import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.ConversationListResponse;
import com.example.coreDemo.payload.response.ConversationResponse;
import com.example.coreDemo.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-list")
@AllArgsConstructor
public class ChatListController {

    private final ConversationService conversationService;

    @GetMapping
    public ApiResponse<List<ConversationListResponse>> getUserChats() {
        return conversationService.getUserConversations();
    }

    @PostMapping
    public ApiResponse<ConversationResponse> createConversation() {
        return conversationService.createConversation();
    }

    @DeleteMapping("/{chatId}")
    public ApiResponse<Void> deleteConversation(@PathVariable String chatId) {
        return conversationService.deleteConversation(chatId);
    }
}
