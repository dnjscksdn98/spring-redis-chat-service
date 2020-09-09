package com.study.SpringBootWebSocketChatServer.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public RedisSubscriber(
            ObjectMapper objectMapper,
            RedisTemplate<String, Object> redisTemplate,
            SimpMessageSendingOperations messagingTemplate
    ) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     *  Description:
     *      - Redis에서 메시지가 발행(Publish)되면 대기하고 있던 onMessage()가 해당 메시지를 받아 처리합니다(Subscribe)
     *
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // Redis에서 발행된 데이터를 받아 Deserialize
            String publishedMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            // ChatMessage 객체로 매핑
            ChatMessage chatMessage = objectMapper.readValue(publishedMessage, ChatMessage.class);

            // WebSocket 구독자에게 채팅 메시지 전송
            messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getChatRoomId(), chatMessage);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}