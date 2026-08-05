FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Se agrega la bandera para permitir repositorios inseguros (HTTP)
RUN mvn clean package -DskipTests -DallowInsecureRepository=true

FROM tomcat:10-jdk17-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
