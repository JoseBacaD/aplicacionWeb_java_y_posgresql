# Compilación con Java 17
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecución en Tomcat 9 con Java 11 actualizado (Soporte Cgroups v2 para Docker/Render)
FROM tomcat:9.0-jre11-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Reemplazar el puerto 8080 por la variable PORT que asigna Render
RUN sed -i 's/port="8080"/port="${env.PORT}"/g' /usr/local/tomcat/conf/server.xml

ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
