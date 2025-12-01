FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-gateway-0.0.1-SNAPSHOT.jar nikatalks-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","nikatalks-gateway.jar"]
