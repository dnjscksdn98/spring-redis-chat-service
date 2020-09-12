package com.study.SpringBootWebSocketChatServer.model;

public class ChatMessagePayload {

    private Long chatRoomId;
    private String sender;
    private String message;
    private MessageType messageType;

    protected ChatMessagePayload() {

    }

    private ChatMessagePayload(Long chatRoomId, String sender, String message, MessageType messageType) {
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.message = message;
        this.messageType = messageType;
    }

    public static ChatMessagePayload of(Long chatRoomId, String sender, String message, MessageType messageType) {
        return new ChatMessagePayload(chatRoomId, sender, message, messageType);
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
