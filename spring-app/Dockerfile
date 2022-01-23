# syntax=docker/dockerfile:1

FROM openjdk:16-alpine3.13

WORKDIR /spring-app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src

CMD ["./mvnw", "spring-boot:run"]