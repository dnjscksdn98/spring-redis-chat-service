package com.study.SpringBootWebSocketChatServer.model;

public class ChatRoom {

    private String id;
    private String name;

    protected ChatRoom() {

    }

    private ChatRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ChatRoom of(String id, String name) {
        return new ChatRoom(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
