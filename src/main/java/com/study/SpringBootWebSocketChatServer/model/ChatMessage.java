package com.study.SpringBootWebSocketChatServer.model;

public class ChatMessage {

    private String id;
    private String chatRoomId;
    private String sender;
    private String message;
    private MessageType messageType;

    protected ChatMessage() {

    }

    private ChatMessage(String id, String chatRoomId, String sender, String message, MessageType messageType) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.message = message;
        this.messageType = messageType;
    }

    public static ChatMessage of(String id, String chatRoomId, String sender, String message, MessageType messageType) {
        return new ChatMessage(id, chatRoomId, sender, message, messageType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
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
