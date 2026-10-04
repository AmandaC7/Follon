FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw -B -DskipTests dependency:go-offline
RUN ./mvnw -B -DskipTests clean package && cp target/*.jar app.jar

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /workspace/app.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
