# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run Spring Boot app
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/application/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
