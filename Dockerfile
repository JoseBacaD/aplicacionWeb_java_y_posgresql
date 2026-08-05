# 1. Compilación estricta en Java 11 (para compatibilidad total de bytecode con Mojarra/JSF)
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Runtime en Tomcat 9 con Java 11 (Misma versión de JVM)
FROM tomcat:9.0-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Desactivar el puerto de shutdown interno
RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

# Mapear el puerto HTTP dinámico
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
