FROM openjdk:11-jdk
COPY target/*SNAPSHOT.jar app.jar

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]