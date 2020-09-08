package com.study.SpringBootWebSocketChatServer.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import com.study.SpringBootWebSocketChatServer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Autowired
    public WebSocketHandler(ObjectMapper objectMapper, ChatService chatService) {
        this.objectMapper = objectMapper;
        this.chatService = chatService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

//        TextMessage textMessage = new TextMessage(payload);
//        session.sendMessage(textMessage);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatService.findChatRoomById(chatMessage.getChatRoomId());
        chatRoom.handleAction(session, chatMessage, chatService);
    }
}
