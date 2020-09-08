package com.study.SpringBootWebSocketChatServer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;


@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @Autowired
    public ChatService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllChatRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findChatRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        String id = UUID.randomUUID().toString();
        ChatRoom newChatRoom = ChatRoom.of(id, name);
        chatRooms.put(id, newChatRoom);
        return newChatRoom;
    }

    public void sendMessage(WebSocketSession session, ChatMessage chatMessage) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
