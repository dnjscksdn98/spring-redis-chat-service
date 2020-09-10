FROM openjdk:11
ADD build/libs/SpringBootWebSocketChatServer-0.0.1-SNAPSHOT.jar chatting.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "chatting.jar"]