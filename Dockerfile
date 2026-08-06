# 1. Compilación
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app

# Limitar estrictamente la memoria que usa Maven para compilar
ENV MAVEN_OPTS="-Xms64m -Xmx256m"

COPY . .
RUN mvn clean package -DskipTests -U

# 2. Runtime en Tomcat 9
FROM tomcat:9.0-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Desactivar puerto de shutdown
RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

# Limitar estrictamente la memoria que usa Tomcat/Java al ejecutarse
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=128m -XX:ReservedCodeCacheSize=64m -XX:+UseSerialGC -Djava.security.egd=file:/dev/./urandom -Dorg.apache.catalina.startup.ContextConfig.jarsToSkip=*.jar -Dorg.apache.catalina.startup.TldConfig.jarsToSkip=*.jar"
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
