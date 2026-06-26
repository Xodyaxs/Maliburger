# Usamos Java 21
FROM eclipse-temurin:21-jdk

LABEL maintainer="karensita@maliburger"

# Copiamos el archivo empaquetado
COPY target/*.jar app.jar

# El puerto de tu API Gateway
EXPOSE 9000

# Comando de arranque
ENTRYPOINT ["java", "-jar", "/app.jar"]