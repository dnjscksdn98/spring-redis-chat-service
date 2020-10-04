package com.study.SpringBootWebSocketChatServer.domain.status;

public enum ChatRoomStatus {
    ACTIVE(0),
    INACTIVE(1);

    private final int code;

    ChatRoomStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
