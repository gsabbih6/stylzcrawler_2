FROM openjdk:14-jdk-alpine
MAINTAINER gsabbih
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]
