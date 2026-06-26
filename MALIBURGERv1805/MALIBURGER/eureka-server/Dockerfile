# Usamos Java 21
FROM eclipse-temurin:21-jdk

LABEL maintainer="karensita@maliburger"

# Copiamos el archivo empaquetado
COPY target/*.jar app.jar

# El puerto oficial de Eureka
EXPOSE 8761

# Comando de arranque
ENTRYPOINT ["java", "-jar", "/app.jar"]