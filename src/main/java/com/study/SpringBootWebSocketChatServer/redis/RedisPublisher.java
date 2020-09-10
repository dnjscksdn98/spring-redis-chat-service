package com.study.SpringBootWebSocketChatServer.redis;

import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    /**
     *  Description:
     *      - 메시지를 Redis Topic에 발행(Publish)합니다.
     *
     */
    public void publish(ChannelTopic topic, ChatMessage message) {
        chatMessageRepository.save(message);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
