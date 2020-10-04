package com.study.SpringBootWebSocketChatServer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.SpringBootWebSocketChatServer.domain.status.MessageType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessagePayload {

    @NotBlank
    private String content;

    @NotBlank
    private String sender;

    @NotBlank
    @JsonProperty(value = "message_type")
    private MessageType messageType;

    @NotNull
    @JsonProperty(value = "chat_room_id")
    private Long chatRoomId;
}
