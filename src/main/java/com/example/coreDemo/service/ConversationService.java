package com.example.coreDemo.service;

import com.example.coreDemo.dto.ConversationDeleteDto;
import com.example.coreDemo.dto.ConversationListDto;
import com.example.coreDemo.entity.ConversationEntity;
import com.example.coreDemo.entity.UserEntity;
import com.example.coreDemo.exception.ConversationErrorCode;
import com.example.coreDemo.exception.ConversationException;
import com.example.coreDemo.helper.ConversationHelper;
import com.example.coreDemo.helper.UserHelper;
import com.example.coreDemo.mapper.ConversationMapper;
import com.example.coreDemo.mapper.UserMapper;
import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.ConversationListResponse;
import com.example.coreDemo.payload.response.ConversationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ConversationService {

    private final ConversationHelper conversationHelper;
    private final ConversationMapper conversationMapper;
    private final UserHelper userHelper;

    public ApiResponse<ConversationResponse> createConversation(){

        UserEntity currentUser = userHelper.getCurrentUser();

        ConversationEntity conversation = new ConversationEntity(currentUser);

        conversationHelper.save(conversation);

        return ApiResponse.<ConversationResponse>builder()
                .status("OK")
                .data(conversationMapper.entityToResponse(conversation))
                .build();
    }

    public ApiResponse<List<ConversationListResponse>> getUserConversations() {

        UserEntity currentUser = userHelper.getCurrentUser();

        List<ConversationListResponse> conversations =
                conversationHelper.getUserConversationList(currentUser.getPhoneNumber())
                        .stream()
                        .map(conversationMapper::listDtoToResponse)
                        .toList();

        return ApiResponse.<List<ConversationListResponse>>builder()
                .status("OK")
                .data(conversations)
                .build();

    }

    public ApiResponse<Void> deleteConversation(String conversationId) {

        UserEntity currentUser = userHelper.getCurrentUser();

        ConversationDeleteDto dto = conversationHelper.getDeleteDto(conversationId);

        if (Objects.equals(dto.userId(), currentUser.getId())) {
            conversationHelper.deleteById(dto.ConversationId());

            return ApiResponse.<Void>builder()
                    .status("OK")
                    .build();
        } else {
            throw new ConversationException(ConversationErrorCode.ConversationNotOwned);
        }
    }

}
