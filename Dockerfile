FROM openjdk:17-jdk-slim AS base

WORKDIR /app

COPY ./rest/target/rest-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]