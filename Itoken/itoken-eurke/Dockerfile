#FROM java:8
FROM openjdk:8-jdk-alpine
ARG JAVA_FILE
COPY ${JAVA_FILE} app.jar
EXPOSE 10086
ENTRYPOINT ["java","-jar","/app.jar"]
