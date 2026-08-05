# 1. Compilación
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Runtime en Tomcat 9
FROM tomcat:9.0-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Desactivar el puerto de Shutdown (8005 -> -1) para evitar que Render envíe el Health Check ahí
RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

# Configurar el puerto HTTP predeterminado
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
