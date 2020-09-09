FROM openjdk:11
ADD build/libs/SpringBootWebSocketChatServer-0.0.1-SNAPSHOT.jar chatting.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "chatting.jar"]