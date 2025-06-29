FROM gradle:jdk21-ubi-minimal AS builder
WORKDIR /app

# Cache dependencies
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle
RUN gradle dependencies --no-daemon

# Copy source code
COPY . .
RUN gradle bootJar --no-daemon

# Run stage
FROM eclipse-temurin:21.0.7_6-jre-ubi9-minimal
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]