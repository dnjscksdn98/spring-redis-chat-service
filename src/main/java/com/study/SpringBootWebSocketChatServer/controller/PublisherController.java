package com.study.SpringBootWebSocketChatServer.controller;

import com.study.SpringBootWebSocketChatServer.domain.model.ChatMessagePayload;
import com.study.SpringBootWebSocketChatServer.redis.RedisPublisher;
import com.study.SpringBootWebSocketChatServer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    @Autowired
    private RedisPublisher redisPublisher;

    @Autowired
    private ChatService chatService;

    /**
     *  Description:
     *      - @MessageMapping 을 통해 WebSocket 으로 들어오는 메시지를 발행
     *      - Client 에서는 prefix 를 붙여서 /pub/chat/message 로 발행 요청을 보내면 해당 메시지 처리
     *      - TODO: url 수정
     *
     */
    @MessageMapping(value = "/chat/message")
    public void sendMessage(ChatMessagePayload message) {
        String topic = message.getChatRoomId().toString();
        redisPublisher.publish(ChannelTopic.of(topic), message);
    }
}
