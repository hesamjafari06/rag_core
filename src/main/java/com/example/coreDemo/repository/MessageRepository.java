package com.example.coreDemo.repository;

import com.example.coreDemo.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByConversation_ConversationIdOrderByCreatedAtAsc(String conversationId);
}
