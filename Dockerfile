FROM openjdk:11-jre-slim

ENV LANG C.UTF-8

ADD build/libs/cryptocalc-api.jar cryptocalc-api.jar

ENTRYPOINT ["java", "-jar", "cryptocalc-api.jar"]

EXPOSE 8080
