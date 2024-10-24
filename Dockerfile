# Usa una imagen de JDK como base
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu proyecto al contenedor
COPY target/jwt-0.0.1-SNAPSHOT.jar app.jar

# Define el comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]