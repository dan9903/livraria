FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ADD data/database.mv.db /tmp/database.h2.db