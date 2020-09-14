package com.study.SpringBootWebSocketChatServer.controller;

import com.study.SpringBootWebSocketChatServer.model.ChatMessage;
import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import com.study.SpringBootWebSocketChatServer.repository.ChatMessageRepository;
import com.study.SpringBootWebSocketChatServer.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class SubscriberController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    /**
     *  Description:
     *      - 채팅방 이름을 요청 파라미터로 전달해서 채팅방 개설
     *
     */
    @PostMapping
    public ChatRoom createChatRoom(@RequestParam String name) {
        return chatRoomRepository.save(ChatRoom.of(name));
    }

    /**
     *  Description:
     *      - 전체 채팅방 목록을 가져옵니다
     *
     */
    @GetMapping
    public List<ChatRoom> getChatRooms() {
        return chatRoomRepository.findAll();
    }

    /**
     *  Description:
     *      - 해당 아이디를 가진 채팅방의 채팅을 가져옵니다
     *      - TODO: 페이징 추가
     *
     */
    @GetMapping(path = "/{chatRoomId}")
    public List<ChatMessage> getChatMessages(@PathVariable("chatRoomId") Long chatRoomId) {
        return chatMessageRepository.findAllByChatRoomId(chatRoomId);
    }
}
