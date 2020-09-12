package com.study.SpringBootWebSocketChatServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 34789214329287934L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    protected ChatRoom() {

    }

    private ChatRoom(String name) {
        this.name = name;
    }

    public static ChatRoom of(String name) {
        return new ChatRoom(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
