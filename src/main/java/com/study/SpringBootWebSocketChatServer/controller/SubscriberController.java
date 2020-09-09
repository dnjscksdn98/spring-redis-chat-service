package com.study.SpringBootWebSocketChatServer.controller;

import com.study.SpringBootWebSocketChatServer.model.ChatRoom;
import com.study.SpringBootWebSocketChatServer.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/chat")
public class SubscriberController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;


    /**
     *  Description:
     *      - 채팅방 이름을 파라미터로 전달해서 채팅방 개설
     *
     */
    @PostMapping(path = "/chat-room")
    public ChatRoom createChatRoom(@RequestParam String name) {
        return chatRoomRepository.save(name);
    }
}
