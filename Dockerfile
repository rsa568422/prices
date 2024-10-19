# Etapa de compilación
FROM maven:3.9.9-amazoncorretto-17 AS build

# Copiar el proyecto padre
COPY ./pom.xml /app/
COPY ./prices-domain /app/prices-domain
COPY ./prices-infrastructure /app/prices-infrastructure
COPY ./prices-application /app/prices-application

# Compilar el proyecto
WORKDIR /app
RUN mvn clean package

# Etapa de ejecución
FROM openjdk:17-jdk-slim

# Copiar el JAR ejecutable desde la etapa de compilación
COPY --from=build /app/prices-application/target/*.jar /app/app.jar

# Exponer el puerto (ajusta según sea necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]