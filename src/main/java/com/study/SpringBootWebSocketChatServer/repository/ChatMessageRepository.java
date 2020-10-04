package com.study.SpringBootWebSocketChatServer.repository;

import com.study.SpringBootWebSocketChatServer.domain.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findAllByChatRoomId(Long id);
}
