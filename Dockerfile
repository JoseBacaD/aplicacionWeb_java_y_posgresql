# Compilación con Java 17 y Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecución en Tomcat 9 con OpenJDK 11
FROM tomcat:9.0-jdk11-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR empaquetado
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Puerto por defecto si PORT no está definido por Render
ENV PORT=8080
EXPOSE ${PORT}

# Script para reconfigurar los puertos de Tomcat adecuadamente
CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT}\\\"/g\" /usr/local/tomcat/conf/server.xml && sed -i \"s/port=\\\"8005\\\"/port=\\\"8006\\\"/g\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]
