package com.example.coreDemo.helper;

import com.example.coreDemo.dto.ConversationDeleteDto;
import com.example.coreDemo.dto.ConversationListDto;
import com.example.coreDemo.entity.ConversationEntity;
import com.example.coreDemo.exception.ConversationErrorCode;
import com.example.coreDemo.exception.ConversationException;
import com.example.coreDemo.exception.UserErrorCode;
import com.example.coreDemo.exception.UserException;
import com.example.coreDemo.repository.ConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConversationHelper {

    private final ConversationRepository conversationRepository;

    public void save(ConversationEntity conversation) {
        conversationRepository.save(conversation);
    }

    public void deleteById(Long id) {
        conversationRepository.deleteById(id);
    }

    public ConversationEntity getByConversationId(String conversationId) {
        return conversationRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new ConversationException(ConversationErrorCode.ConversationNotFound));
    }

    public List<ConversationListDto> getUserConversationList(String phoneNumber) {
        return conversationRepository.findConversationListByPhoneNumber(phoneNumber);
    }

    public ConversationDeleteDto getDeleteDto(String conversationId) {
        return conversationRepository.findDeleteConversationByConversationId(conversationId)
                .orElseThrow(() -> new ConversationException(ConversationErrorCode.ConversationNotFound));
    }
}
