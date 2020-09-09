package com.study.SpringBootWebSocketChatServer.repository;

import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ChatMessageRepository {

    private Map<String, ChatMessage> chatMessages;

    @Autowired
    public ChatMessageRepository() {
        this.chatMessages = new LinkedHashMap<>();
    }

    public List<ChatMessage> findAll(String chatRoomId) {
        return chatMessages.values()
                .stream()
                .filter(msg -> msg.getChatRoomId().contentEquals(chatRoomId))
                .collect(Collectors.toList());
    }

    public ChatMessage save(ChatMessage message) {
        chatMessages.put(message.getChatRoomId(), message);
        return message;
    }
}
