## 🚀 Chat Server using Redis Pub/Sub, WebSockets, and Spring Boot

### Run Docker

```bash
docker run -d -p 6379:6379 -v /data --name redis redis
```

```bash
docker run -d -p 8080:8080 --name chat-server dnjscksdn98/websocket-chat:1.0.2
```

### Run in localhost

```bash
docker run -d -p 8080:8080 --name chat-server dnjscksdn98/websocket-chat:1.0.2-beta
```

## 🚀 API Documentation

### ✓ Connecting to WebSockets

```
ws://{domainAddress}:8080/ws/chat
```

### ✓ Publish path

```
/pub/chat/message
```

### ✓ Subscribe path

```
/sub/chat/room/{chatRoomId}
```

### ✓ Create Chat Room

> POST /chat

|Attribute   	|Type   	|Required   	|Description   	|
|---	        |---	        |---	        |---	        |
|name   	|string   	|yes   	|Chat Room Name   	|

**Example Response**

```json
{
    "id": 1,
    "name": "yapp 17th"
}
```

### ✓ Retrieve Chat Rooms

> GET /chat

**Example Response**

```json
[
    {
        "id": 1,
        "name": "yapp 17th"
    },
    {
        "id": 2,
        "name": "marketing team"
    }
]
```

### ✓ Retreive Chat Messages from Chat Room

> GET /chat/{chatRoomId}

**Example Response**

```json
[
    {
        "chatRoomId": 1,
        "sender": "tester",
        "message": "hello",
        "messageType": "TALK"
    },
    {
        "chatRoomId": 1,
        "sender": "tester",
        "message": "bye",
        "messageType": "TALK"
    }
]
```
