package com.study.SpringBootWebSocketChatServer.model;

import javax.persistence.*;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;

    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    protected ChatMessage() {

    }

    private ChatMessage(Long chatRoomId, String sender, String message, MessageType messageType) {
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.message = message;
        this.messageType = messageType;
    }

    public static ChatMessage of(Long chatRoomId, String sender, String message, MessageType messageType) {
        return new ChatMessage(chatRoomId, sender, message, messageType);
    }

    public static ChatMessage of(ChatMessagePayload payload) {
        return new ChatMessage(payload.getChatRoomId(), payload.getSender(), payload.getMessage(), payload.getMessageType());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
