package com.study.SpringBootWebSocketChatServer.repository;

import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRooms;

    @Autowired
    public ChatRoomRepository() {
        this.chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAll() {
        List<ChatRoom> findAll = new ArrayList<>(chatRooms.values());
        Collections.reverse(findAll);
        return findAll;
    }

    public ChatRoom findById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoom save(String name) {
        ChatRoom chatRoom = ChatRoom.of(UUID.randomUUID().toString(), name);
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }
}
