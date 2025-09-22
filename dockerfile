# Etapa 1: Build con Maven + JDK 24
FROM eclipse-temurin:24-jdk-jammy AS build

WORKDIR /app

# Copiar los archivos necesarios para descargar dependencias
COPY pom.xml .
COPY src src

# Ejecutar build Maven (omitiendo tests si quieres) para generar el .jar
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera de ejecución con solo el runtime
FROM eclipse-temurin:24-jre-jammy AS runtime

WORKDIR /app

# Copia el jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Puerto que usará tu Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java","-jar","/app/app.jar"]

