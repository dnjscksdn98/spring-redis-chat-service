package com.study.SpringBootWebSocketChatServer.redis;

import com.study.SpringBootWebSocketChatServer.domain.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.domain.model.ChatMessagePayload;
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
     *      - 메시지를 Redis Topic(채팅방 고유 아이디)에 발행(Publish)합니다.
     *
     */
    public void publish(ChannelTopic topic, ChatMessagePayload message) {
        ChatMessage publishMessage = ChatMessage.of(
                Long.parseLong(topic.getTopic()),
                message.getSender(),
                message.getMessage(),
                message.getMessageType()
        );
        chatMessageRepository.save(publishMessage);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
