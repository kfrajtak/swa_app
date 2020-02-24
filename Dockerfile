FROM openjdk:15-jdk-alpine3.11
COPY ./target/app-0.0.1-SNAPSHOT.jar /usr/app/app.jar
COPY ./config.properties /usr/app/config.properties
ENTRYPOINT ["java","-jar","/usr/app/app.jar", "--spring.config.location=/usr/app/config.properties"]  