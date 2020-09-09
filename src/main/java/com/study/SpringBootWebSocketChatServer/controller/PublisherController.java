package com.study.SpringBootWebSocketChatServer.controller;

import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.model.MessageType;
import com.study.SpringBootWebSocketChatServer.redis.RedisPublisher;
import com.study.SpringBootWebSocketChatServer.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    @Autowired
    private RedisPublisher redisPublisher;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage message) {
        if (MessageType.ENTER.equals(message.getMessageType())) {
            chatRoomRepository.enterChatRoom(message.getChatRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        redisPublisher.publish(chatRoomRepository.getTopic(message.getChatRoomId()), message);
    }
}
