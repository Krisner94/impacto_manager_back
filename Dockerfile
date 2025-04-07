FROM ubuntu:latest
LABEL authors="rhama"

# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho
COPY target/impacto_manager_back-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

#ENTRYPOINT ["top", "-b"]