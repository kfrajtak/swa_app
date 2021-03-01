FROM openjdk:15-jdk-alpine3.11
COPY ./target/app-0.0.1-SNAPSHOT.jar /usr/app/app.jar
COPY ./config.properties /usr/app/config.properties
ENTRYPOINT ["java","-jar","/usr/app/app.jar", "--spring.config.location=/usr/app/config.properties"]  

#ENV ELASTIC_APM_VERSION "1.8.0"
#WORKDIR /opt/apm
#RUN wget -O apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/$ELASTIC_APM_VERSION/elastic-apm-agent-$ELASTIC_APM_VERSION.jar
