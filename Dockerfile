# Usa una imagen base de Java
FROM openjdk:21-jdk-alpine
# Copia el JAR generado al contenedor
COPY target/*.jar app.jar
# Expone el puerto de la aplicación

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "/app.jar"]