FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-classes-0.0.1-SNAPSHOT.jar nikatalks-classes.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","nikatalks-classes.jar"]
