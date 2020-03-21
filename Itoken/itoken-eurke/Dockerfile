#FROM java:8
FROM openjdk:8-jdk-alpine
ARG JAVA_FILE
COPY ${JAVA_FILE} app.jar
EXPOSE 10086
CMD echo ${JAVA_FILE}

ENTRYPOINT ["echo","${JAVA_FILE}","java","-jar","/app.jar"]
