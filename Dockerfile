# Etapa 1: build com Maven
FROM maven:3.9.4-eclipse-temurin-21 as builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com JAR
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /build/target/impacto_manager_back-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
