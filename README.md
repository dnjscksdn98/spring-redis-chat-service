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
