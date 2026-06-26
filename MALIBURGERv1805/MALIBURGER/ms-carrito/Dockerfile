FROM eclipse-temurin:21-jdk
LABEL maintainer="karensita@maliburger"
COPY target/*.jar app.jar

# CAMBIA ESTE NÚMERO por el puerto de este microservicio
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app.jar"]