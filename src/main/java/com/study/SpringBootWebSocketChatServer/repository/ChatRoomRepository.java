package com.study.SpringBootWebSocketChatServer.repository;

import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import com.study.SpringBootWebSocketChatServer.redis.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private RedisSubscriber redisSubscriber;  // 구독 처리 서비스
    private RedisMessageListenerContainer redisMessageListener;  // 채팅방(Topic)에 발행되는 메시지를 처리할 Listener

    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> hashChatRoom;
    private Map<String, ChannelTopic> topics;

    @Autowired
    public ChatRoomRepository(
            RedisSubscriber redisSubscriber,
            RedisMessageListenerContainer redisMessageListener,
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.hashChatRoom = redisTemplate.opsForHash();
        this.topics = new HashMap<>();
        this.redisSubscriber = redisSubscriber;
        this.redisMessageListener = redisMessageListener;
        this.redisTemplate = redisTemplate;
    }

    public List<ChatRoom> findAll() {
        return hashChatRoom.values(CHAT_ROOMS);
    }

    public ChatRoom findById(String id) {
        return hashChatRoom.get(CHAT_ROOMS, id);
    }

    /**
     *  Description:
     *      - 채팅방 생성: 서버간 채팅방 공유를 위해 Redis Hash에 저장합니다.
     *
     */
    public ChatRoom save(String name) {
        ChatRoom chatRoom = ChatRoom.of(UUID.randomUUID().toString(), name);
        hashChatRoom.put(CHAT_ROOMS, chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    /**
     *  Description:
     *      - Redis에 Topic을 만들고 pub/sub 통신을 하기 위해 Listener를 설정합니다.
     */
    public void enterChatRoom(String id) {
        ChannelTopic topic = topics.get(id);
        if (topic == null) {
            topic = new ChannelTopic(id);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(id, topic);
        }
    }

    public ChannelTopic getTopic(String id) {
        return topics.get(id);
    }
}
