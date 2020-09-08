package com.study.SpringBootWebSocketChatServer.model;

import com.study.SpringBootWebSocketChatServer.service.ChatService;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class ChatRoom {

    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    protected ChatRoom() {

    }

    private ChatRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ChatRoom of(String id, String name) {
        return new ChatRoom(id, name);
    }

    public void handleAction(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getMessageType().equals(MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + " 님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    private void sendMessage(ChatMessage chatMessage, ChatService chatService) {
        sessions
                .parallelStream()
                .forEach(session -> chatService.sendMessage(session, chatMessage));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<WebSocketSession> sessions) {
        this.sessions = sessions;
    }
}
