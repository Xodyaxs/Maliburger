# Quitamos el -alpine para usar la imagen estándar compatible con chips M1/M2/M3
FROM eclipse-temurin:21-jdk

LABEL maintainer="karensita@maliburger"

COPY target/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/app.jar"]