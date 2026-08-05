# Compilación con Java 17 y Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecución en Tomcat 9 con OpenJDK 11 (estabilidad garantizada para javax.*)
FROM tomcat:9.0-jdk11-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
