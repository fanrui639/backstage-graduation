FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/backstage-graduation-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-Dspring.cloud.config.profile=pro","-jar","/app.jar"]
