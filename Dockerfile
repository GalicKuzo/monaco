# Etapa de build: usar imagen que tiene Maven + Java 21
FROM maven:3.9.7-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copia pom.xml primero para descargar dependencias
COPY pom.xml .  

# Copia el código fuente
COPY src src

# Construir el proyecto, omitiendo tests si deseas
RUN mvn clean package -DskipTests

# Etapa de runtime: usar solo JRE de Java 21 para que la imagen final sea más liviana
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Copiar el .jar generado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usa tu aplicación Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
