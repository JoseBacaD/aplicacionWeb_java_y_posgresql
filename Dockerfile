# 1. Compilación (Build)
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app

# Restricción EXTREMA de RAM para Maven para evitar el error 137 de Render
ENV MAVEN_OPTS="-Xms64m -Xmx128m -XX:+UseSerialGC"

# Copiamos archivos y compilamos en modo Batch (-B) para no saturar los logs
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -B

# 2. Ejecución (Runtime)
FROM tomcat:9.0-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

# Restricción de RAM para Tomcat
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=128m -XX:ReservedCodeCacheSize=64m -XX:+UseSerialGC -Djava.security.egd=file:/dev/./urandom -Dorg.apache.catalina.startup.ContextConfig.jarsToSkip=*.jar -Dorg.apache.catalina.startup.TldConfig.jarsToSkip=*.jar"
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
