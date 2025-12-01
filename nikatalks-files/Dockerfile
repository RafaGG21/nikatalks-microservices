FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-files-0.0.1-SNAPSHOT.jar nikatalks-files.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","nikatalks-files.jar"]
