FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-publications-0.0.1-SNAPSHOT.jar nikatalks-publications.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","nikatalks-publications.jar"]
