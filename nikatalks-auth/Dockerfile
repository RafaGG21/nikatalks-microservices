FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-auth-0.0.1-SNAPSHOT.jar nikatalks-auth.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","nikatalks-auth.jar"]
