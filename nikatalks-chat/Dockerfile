FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-chat-0.0.1-SNAPSHOT.jar nikatalks-chat.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","nikatalks-chat.jar"]
