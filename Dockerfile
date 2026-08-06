FROM tomcat:9.0-jdk11-alpine

RUN rm -rf /usr/local/tomcat/webapps/*

COPY deploy/*.war /usr/local/tomcat/webapps/ROOT.war

RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/g' /usr/local/tomcat/conf/server.xml

ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=128m -XX:+UseSerialGC"
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]