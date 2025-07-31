FROM gradle:8.14.3-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/agendador-tarefas.jar"]