package com.example.coreDemo.mapper;

import com.example.coreDemo.entity.ConversationEntity;
import com.example.coreDemo.entity.MessageEntity;
import com.example.coreDemo.payload.request.SendMessageRequest;
import com.example.coreDemo.payload.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageEntity toEntity(SendMessageRequest request, ConversationEntity conversation) {
        return MessageEntity.builder()
                .content(request.getContent())
                .conversation(conversation)
                .build();
    }

    public MessageResponse entityToResponse(MessageEntity entity) {
        return MessageResponse.builder()
                .messageId(entity.getMessageId())
                .content(entity.getContent())
                .sendTime(entity.getCreatedAt().toString().substring(0, 10))
                .conversationId(entity.getConversation().getConversationId())
                .build();
    }
}
