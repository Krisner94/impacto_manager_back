FROM openjdk:21-jdk-slim

LABEL authors="rhama"

WORKDIR /app

COPY target/impacto_manager_back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
