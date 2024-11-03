# Use a base image with JDK
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/quiz*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 9000
