package com.study.SpringBootWebSocketChatServer.repository;

import com.study.SpringBootWebSocketChatServer.domain.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findById(Long id);
}
