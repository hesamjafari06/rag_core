package com.example.coreDemo.mapper;

import com.example.coreDemo.dto.ConversationListDto;
import com.example.coreDemo.entity.ConversationEntity;
import com.example.coreDemo.payload.response.ConversationListResponse;
import com.example.coreDemo.payload.response.ConversationResponse;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper {

    public ConversationResponse entityToResponse(ConversationEntity entity) {
        return ConversationResponse.builder()
                .conversationId(entity.getConversationId())
                .createdAt(entity.getCreatedAt().toString().substring(0, 10))
                .name(entity.getName())
                .build();
    }

    public ConversationListResponse listDtoToResponse(ConversationListDto dto) {
        return ConversationListResponse.builder()
                .name(dto.name())
                .createdAt(dto.createdAt().toString().substring(0, 10))
                .build();
    }

}
