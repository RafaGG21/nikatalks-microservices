FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/nikatalks-payments-0.0.1-SNAPSHOT.jar nikatalks-payments.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","nikatalks-payments.jar"]
