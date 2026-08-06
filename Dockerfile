FROM tomcat:9.0-jdk11-temurin

# Limpiar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR compilado desde tu carpeta local
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Desactivar puerto de shutdown
RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

# Opciones de JVM optimizadas para consumo minimo de RAM en Render
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=128m -XX:ReservedCodeCacheSize=64m -XX:+UseSerialGC -Djava.security.egd=file:/dev/./urandom -Dorg.apache.catalina.startup.ContextConfig.jarsToSkip=*.jar -Dorg.apache.catalina.startup.TldConfig.jarsToSkip=*.jar"
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]