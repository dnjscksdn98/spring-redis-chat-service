package com.study.SpringBootWebSocketChatServer.domain.model;

import com.study.SpringBootWebSocketChatServer.domain.status.ChatRoomStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 34789214329287934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(value = AccessLevel.PRIVATE)
    private String name;

    @Setter(value = AccessLevel.PRIVATE)
    @Enumerated(value = EnumType.ORDINAL)
    private ChatRoomStatus status = ChatRoomStatus.ACTIVE;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> messages = new ArrayList<>();

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public static ChatRoomBuilder getBuilder() {
        return new ChatRoomBuilder();
    }

    public static class ChatRoomBuilder {
        private String name;

        public ChatRoomBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ChatRoom build() {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(name);
            return chatRoom;
        }
    }
}
