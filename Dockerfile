FROM tomcat:9.0-jdk11-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY deploy/*.war /usr/local/tomcat/webapps/ROOT.war

RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

ENV JAVA_OPTS="-Xms64m -Xmx220m -XX:MaxMetaspaceSize=110m -XX:ReservedCodeCacheSize=48m -XX:+UseSerialGC -Djava.security.egd=file:/dev/./urandom -Dorg.apache.catalina.startup.ContextConfig.jarsToSkip=*.jar -Dorg.apache.catalina.startup.TldConfig.jarsToSkip=*.jar"
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]