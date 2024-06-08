FROM eclipse-temurin:17-jdk-alpine

RUN mkdir /app
WORKDIR /app

ARG JAR_FILE=target/sistemaTroca-*.jar

COPY ${JAR_FILE} /app/application.jar
EXPOSE 8080

RUN chown -R 1001:1001 /app

ENTRYPOINT java -jar /app/application.jar