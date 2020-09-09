package com.study.SpringBootWebSocketChatServer.controller;

import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    /**
     *  Description:
     *      - @MessageMapping 을 통해 WebSocket 으로 들어오는 메시지 발행을 처리합니다.
     *
     */
    @MessageMapping(value = "/chat/message")
    public void send(ChatMessage message) {
        if (MessageType.ENTER.equals(message.getMessageType())) {
            message.setMessage(message.getSender() + " 님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }
}
