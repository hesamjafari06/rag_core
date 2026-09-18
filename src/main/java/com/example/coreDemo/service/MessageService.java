package com.example.coreDemo.service;

import com.example.coreDemo.entity.ConversationEntity;
import com.example.coreDemo.entity.MessageEntity;
import com.example.coreDemo.entity.UserEntity;
import com.example.coreDemo.exception.ConversationErrorCode;
import com.example.coreDemo.exception.ConversationException;
import com.example.coreDemo.helper.ConversationHelper;
import com.example.coreDemo.helper.MessageHelper;
import com.example.coreDemo.helper.UserHelper;
import com.example.coreDemo.mapper.MessageMapper;
import com.example.coreDemo.payload.request.SendMessageRequest;
import com.example.coreDemo.payload.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final UserHelper userHelper;
    private final MessageHelper messageHelper;
    private final ConversationHelper conversationHelper;
    private final MessageMapper messageMapper;


    @Transactional
    public MessageResponse sendMessage(
            SendMessageRequest request,
            Principal principal
    ) {

        UserEntity currentUser = userHelper.findUserByPhoneNumber(principal.getName());

        ConversationEntity conversation =
                conversationHelper.getByConversationId(
                        request.getConversationId()
                );

        if (!conversation.getUser().getId().equals(currentUser.getId())) {
            throw new ConversationException(ConversationErrorCode.ConversationNotOwned);
        }

        MessageEntity message = messageMapper.toEntity(request, conversation);

        messageHelper.save(message);

        return messageMapper.entityToResponse(message);
    }

    public List<MessageResponse> getConversationMessages(String conversationId) {
        return messageHelper.getConversationMessages(conversationId).stream().map(messageMapper::entityToResponse).toList();
    }
}
