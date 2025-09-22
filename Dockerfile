# Etapa de build (compilación)
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar pom.xml para que Maven descargue dependencias primero
COPY pom.xml .

# Copiar el código fuente
COPY src src

# Construir el proyecto con Maven, omitiendo tests si quieres
RUN mvn clean package -DskipTests

# Etapa de runtime (ejecución) con solo JRE para tener la imagen más liviana
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copiar el .jar generado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
