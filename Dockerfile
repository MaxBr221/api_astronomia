# Estágio 1: Build da aplicação com Maven e Java 21
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia arquivo de configuração do Maven
COPY pom.xml .

# Baixa dependências para cache
RUN mvn dependency:go-offline -B || true

# Copia o código-fonte
COPY src ./src

# Compila o projeto sem rodar testes
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final otimizada para execução (JRE 21)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o artefato .jar gerado no build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Inicia a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
