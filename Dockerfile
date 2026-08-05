# Compilación con Java 17 y Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecución en Tomcat 9
FROM tomcat:9.0-jdk11-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

ENV PORT=8080
ENV JAVA_OPTS="-Xms128m -Xmx384m -XX:+UseSerialGC"
EXPOSE ${PORT}

CMD ["sh", "-c", "export CATALINA_OPTS=\"-Dbio.http.port=${PORT} -Dhttp.port=${PORT}\" && catalina.sh run"]
